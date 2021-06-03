import pickle
import cv2
from flask import Flask, render_template, Response
import tensorflow as tf
import numpy as np
import json
import os

app = Flask(__name__)

@app.route('/')
def anyname():
    """Video streaming home page."""
    return render_template('index.html')

if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0", port=int(os.environ.get("PORT", 8080)))

z = 0
dir_vid = './parking lot 2.mp4'
loc_spot = './spot space/spot2.pickle'

with(open(loc_spot, 'rb')) as loc:
    x = pickle.load(loc)
spot_available = np.zeros(len(x))
boxes = []
for box in x:
    a, b, c, d = box
    boxes += [[int(a / 2), int(b / 2), int(c / 2), int(d / 2)]]

train_model = 'train1.h5'
model = tf.keras.models.load_model(train_model)


def prediction(input_model):
    generate_id = []

    for i in input_model:
        predict_model = model.predict(i)
        generate_id += [np.argmax(predict_model[0])]

    return generate_id


def convert(a, b):
    zipped = zip(a, b)
    op = dict(zipped)
    return op


def gen():
    """Video streaming generator function."""
    global z, spot_available

    status = None
    cap = cv2.VideoCapture(dir_vid)
    # Read until video is completed
    while cap.isOpened():
        # Capture frame-by-frame
        ret, img = cap.read()
        if not ret:
            cap = cv2.VideoCapture(dir_vid)
            continue
        if ret:
            img = cv2.resize(img, (0, 0), fx=0.5, fy=0.5)
            z += 1
            if z % 10 == 0:
                status = True

            if status:
                img_crop = []
                for i, box in enumerate(x):
                    proses1 = img[boxes[i][1]:boxes[i][1] + boxes[i][3], boxes[i][0]:boxes[i][0] + boxes[i][2]]
                    proses2 = cv2.resize(proses1, (50, 50)) / 255.0
                    img_crop += [np.expand_dims(proses2, axis=0)]

                spot_available = prediction(img_crop)
                status = False

            json_objs = []
            str_s = ['id', 'value']
            for i in range(len(spot_available)):
                conv = convert(str_s, [i+1, bool(spot_available[i])])
                json_obj = json.dumps(conv)
                json_objs += [json_obj]

            print('spot avail', spot_available)
            for i in range(len(spot_available)):
                if spot_available[i] == 0:
                    cv2.rectangle(img, (boxes[i][0], boxes[i][1]),
                                  (boxes[i][0] + boxes[i][2], boxes[i][1] + boxes[i][3]), (0, 255, 0), 3)
                cv2.putText(img, str(i), (boxes[i][0] + 15, boxes[i][1] + 40), cv2.FONT_HERSHEY_SIMPLEX, 1,
                            (0, 255, 255), 3)

            cv2.putText(img, 'available spot: {}'.format(len(spot_available) - sum(spot_available)), (50, 510),
                        cv2.FONT_HERSHEY_COMPLEX, 1, (255, 0, 0), 3)

            frame = cv2.imencode('.jpg', img)[1].tobytes()
            yield (b'--frame\r\n'b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')


@app.route('/video_feed')
def video_feed():
    """Video streaming route. Put this in the src attribute of an img tag."""
    return Response(gen(),
                    mimetype='multipart/x-mixed-replace; boundary=frame')