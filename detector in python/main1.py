import pickle
import cv2
from flask import Flask, render_template, Response
import tensorflow as tf
import numpy as np

app = Flask(__name__)

@app.route('/')
def anyname():
    """Video streaming home page."""
    return render_template('index.html')


#video = cv2.VideoCapture('parking lot 1.mp4')
loc_spot = 'spot.pickle'
with(open(loc_spot, 'rb')) as loc:
    x = pickle.load(loc)

train_model = 'train.h5'
model = tf.keras.models.load_model(train_model)
spot_available = np.zeros(len(x))
z = 0
boxes = []
for box in x:
   a, b, c, d = box
   boxes += [int(a / 2), int(b / 2), int(c / 2), int(d / 2)]

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
    status = None
    global spot_available, z
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
            z += 1
            print(z)
            if z % 10 == 0:
                status = True

            if status:
                img_crop = []
                for i, box in enumerate(x):
                    proses1 = img[boxes[i][1]:boxes[i][1]+boxes[i][3], boxes[i][0]:boxes[i][0]+boxes[i][2]]
                    proses2 = cv2.resize(proses1, (50,50))/255.0
                    img_crop += [np.expand_dims(proses2, axis=0)]
                    

                print('img crop', len(img_crop))
                spot_available = prediction(img_crop)
                status = False

            print('spot avail', spot_available)
            for i in spot_available:
                if i == 1:
                    cv2.rectangle(img, (boxes[i][0], boxes[i][1]), (boxes[i][0] + boxes[i][2], boxes[i][1] + boxes[i][3]), (255, 255, 0), 3)

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
    app.run(debug=True, host='192.168.1.138', threaded=True)