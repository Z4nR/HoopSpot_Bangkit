import pickle
import cv2
from flask import Flask, render_template, Response
import tensorflow as tf
import numpy as np

app = Flask(__name__)

@app.route('/')
def anyname():
    """Video streaming home page."""
    return render_template('onscr.html')

def prediction(input_model):
    # print('img', len(img))
    generate_id = []

    # images = img/255.0
    # print('dim', len(input_model))
    for i in input_model:
        predict_model = model.predict(i)
        generate_id += [np.argmax(predict_model[0])]
    print(len(generate_id))
    return generate_id

def gen():
    """Video streaming generator function."""
    cap = cv2.VideoCapture('parking lot 3.mp4')

    # Read until video is completed
    while (cap.isOpened()):
        # Capture frame-by-frame
        ret, img = cap.read()
        # print(type(ret), 't', type(img))
        # print(type(img), np.shape(img))
        if ret:
            img = cv2.resize(img, (0, 0), fx=0.5, fy=0.5)
            # print(type(img), np.shape(img))
            img_crop = []
            for i, box in enumerate(x):
                a, b, c, d = box
                a, b, c, d = int(a/2), int(b/2), int(c/2), int(d/2)
                proses1 = img[b:b+d, a:a+c]
                proses2 = cv2.resize(proses1, (50,50))/255.0
                img_crop += [np.expand_dims(proses2, axis=0)]
            print('img crop', len(img_crop))
            spot_available = prediction(img_crop)
            print('spot avail', spot_available)
            frame = cv2.imencode('.jpg', img)[1].tobytes()
            # time.sleep(0.0000000000000000000000001)
            yield (b'--frame\r\n'b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')
        else:
            break

#
@app.route('/video_feed')
def video_feed():
    """Video streaming route. Put this in the src attribute of an img tag."""
    return Response(gen(),
                    mimetype='multipart/x-mixed-replace; boundary=frame')

if __name__ == "__main__":
    video = cv2.VideoCapture('parking lot 3.mp4')
    loc_spot = 'spot.pickle'
    with(open(loc_spot, 'rb')) as loc:
        x = pickle.load(loc)
        train_model = 'train.h5'
    model = tf.keras.models.load_model(train_model)
    app.run(debug=True, host='192.168.1.138', threaded=True)
