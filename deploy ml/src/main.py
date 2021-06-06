import pickle
import cv2
from flask import Flask, render_template, Response
import tensorflow as tf
import numpy as np
import json
import os

app = Flask(__name__, template_folder='template')


@app.route('/')
def anyname():
    """Video streaming home page."""
    return render_template("index.html")


@app.route('/index_parking1')
def index_1():
    return render_template('index1.html')


@app.route('/index_parking2')
def index_2():
    return render_template('index2.html')


@app.route('/index_parking3')
def index_3():
    return render_template('index3.html')


@app.route('/index_parking4')
def index_4():
    return render_template('index4.html')


@app.route('/index_parking5')
def index_5():
    return render_template('index5.html')


z = 0
dir_vid = 'parking 1.mp4'
loc_spot1 = 'spot space/spot1.pickle'

with(open(loc_spot1, 'rb')) as loc:
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


def park1():
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
                conv = convert(str_s, [i + 1, bool(spot_available[i])])
                json_obj = json.dumps(conv)
                json_objs += [json_obj]

            print('spot avail', spot_available)
            for i in range(len(spot_available)):
                if spot_available[i] == 0:
                    cv2.rectangle(img, (boxes[i][0], boxes[i][1]),
                                  (boxes[i][0] + boxes[i][2], boxes[i][1] + boxes[i][3]), (0, 255, 0), 3)
                cv2.putText(img, str(i+1), (boxes[i][0] + 15, boxes[i][1] + 40), cv2.FONT_HERSHEY_SIMPLEX, 1,
                            (0, 255, 255), 3)

            cv2.putText(img, 'available spot: {}'.format(len(spot_available) - sum(spot_available)), (50, 510),
                        cv2.FONT_HERSHEY_COMPLEX, 1, (255, 0, 0), 3)

            frame = cv2.imencode('.jpg', img)[1].tobytes()
            yield (b'--frame\r\n'b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')


dir_vid2 = 'parking 2.mp4'
loc_spot2 = 'spot space/spot2.pickle'

with(open(loc_spot2, 'rb')) as loc:
    x2 = pickle.load(loc)

spot_available2 = np.zeros(len(x2))

boxes2 = []
for box in x2:
    a, b, c, d = box
    boxes2 += [[int(a / 2), int(b / 2), int(c / 2), int(d / 2)]]


def park2():
    """Video streaming generator function."""
    global z, spot_available2

    status = None
    cap = cv2.VideoCapture(dir_vid2)
    # Read until video is completed
    while cap.isOpened():
        # Capture frame-by-frame
        ret, img = cap.read()
        if not ret:
            cap = cv2.VideoCapture(dir_vid2)
            continue
        if ret:
            img = cv2.resize(img, (0, 0), fx=0.5, fy=0.5)
            z += 1
            if z % 5 == 0:
                status = True

            if status:
                img_crop = []
                for i, box in enumerate(x2):
                    proses1 = img[boxes2[i][1]:boxes2[i][1] + boxes2[i][3], boxes2[i][0]:boxes2[i][0] + boxes2[i][2]]
                    proses2 = cv2.resize(proses1, (50, 50)) / 255.0
                    img_crop += [np.expand_dims(proses2, axis=0)]

                spot_available2 = prediction(img_crop)
                status = False

            json_objs = []
            str_s = ['id', 'value']
            for i in range(len(spot_available2)):
                conv = convert(str_s, [i + 1, bool(spot_available2[i])])
                json_obj = json.dumps(conv)
                json_objs += [json_obj]

            print('spot avail', spot_available2)
            for i in range(len(spot_available2)):
                if spot_available2[i] == 0:
                    cv2.rectangle(img, (boxes2[i][0], boxes2[i][1]),
                                  (boxes2[i][0] + boxes2[i][2], boxes2[i][1] + boxes2[i][3]), (0, 255, 0), 3)
                cv2.putText(img, str(i+1), (boxes2[i][0] + 15, boxes2[i][1] + 40), cv2.FONT_HERSHEY_SIMPLEX, 1,
                            (0, 255, 255), 3)

            cv2.putText(img, 'available spot: {}'.format(len(spot_available2) - sum(spot_available2)), (50, 510),
                        cv2.FONT_HERSHEY_COMPLEX, 1, (255, 0, 0), 3)

            frame = cv2.imencode('.jpg', img)[1].tobytes()
            yield (b'--frame\r\n'b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')


dir_vid3 = 'parking 3.mp4'
loc_spot3 = 'spot space/spot3.pickle'

with(open(loc_spot3, 'rb')) as loc:
    x3 = pickle.load(loc)

spot_available3 = np.zeros(len(x3))

boxes3 = []
for box in x3:
    a, b, c, d = box
    boxes3 += [[int(a / 2), int(b / 2), int(c / 2), int(d / 2)]]


def park3():
    """Video streaming generator function."""
    global z, spot_available3

    status = None
    cap = cv2.VideoCapture(dir_vid3)
    # Read until video is completed
    while cap.isOpened():
        # Capture frame-by-frame
        ret, img = cap.read()
        if not ret:
            cap = cv2.VideoCapture(dir_vid3)
            continue
        if ret:
            img = cv2.resize(img, (0, 0), fx=0.5, fy=0.5)
            z += 1
            if z % 10 == 0:
                status = True

            if status:
                img_crop = []
                for i, box in enumerate(x3):
                    proses1 = img[boxes3[i][1]:boxes3[i][1] + boxes3[i][3], boxes3[i][0]:boxes3[i][0] + boxes3[i][2]]
                    proses2 = cv2.resize(proses1, (50, 50)) / 255.0
                    img_crop += [np.expand_dims(proses2, axis=0)]

                spot_available3 = prediction(img_crop)
                status = False

            #json_objs = []
            #str_s = ['id', 'value']
            #for i in range(len(spot_available3)):
                #conv = convert(str_s, [i + 1, bool(spot_available3[i])])
                #json_obj = json.dumps(conv)
                #json_objs += [json_obj]

            print('spot avail', spot_available3)
            for i in range(len(spot_available3)):
                if spot_available3[i] == 0:
                    cv2.rectangle(img, (boxes3[i][0], boxes3[i][1]),
                                  (boxes3[i][0] + boxes3[i][2], boxes3[i][1] + boxes3[i][3]), (0, 255, 0), 3)
                cv2.putText(img, str(i+1), (boxes3[i][0] + 15, boxes3[i][1] + 40), cv2.FONT_HERSHEY_SIMPLEX, 1,
                            (0, 255, 255), 3)

            cv2.putText(img, 'available spot: {}'.format(len(spot_available3) - sum(spot_available3)), (50, 510),
                        cv2.FONT_HERSHEY_COMPLEX, 1, (255, 0, 0), 3)

            frame = cv2.imencode('.jpg', img)[1].tobytes()
            yield (b'--frame\r\n'b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')


dir_vid4 = 'parking 4.mp4'
loc_spot4 = 'spot space/spot4.pickle'

with(open(loc_spot4, 'rb')) as loc:
    x4 = pickle.load(loc)

spot_available4 = np.zeros(len(x3))

boxes4 = []
for box in x4:
    a, b, c, d = box
    boxes4 += [[int(a / 2), int(b / 2), int(c / 2), int(d / 2)]]


def park4():
    """Video streaming generator function."""
    global z, spot_available4

    status = None
    cap = cv2.VideoCapture(dir_vid4)
    # Read until video is completed
    while cap.isOpened():
        # Capture frame-by-frame
        ret, img = cap.read()
        if not ret:
            cap = cv2.VideoCapture(dir_vid4)
            continue
        if ret:
            img = cv2.resize(img, (0, 0), fx=0.5, fy=0.5)
            z += 1
            if z % 10 == 0:
                status = True

            if status:
                img_crop = []
                for i, box in enumerate(x4):
                    proses1 = img[boxes4[i][1]:boxes4[i][1] + boxes4[i][3], boxes4[i][0]:boxes4[i][0] + boxes4[i][2]]
                    proses2 = cv2.resize(proses1, (50, 50)) / 255.0
                    img_crop += [np.expand_dims(proses2, axis=0)]

                spot_available4 = prediction(img_crop)
                status = False

            json_objs = []
            str_s = ['id', 'value']
            for i in range(len(spot_available4)):
                conv = convert(str_s, [i + 1, bool(spot_available4[i])])
                json_obj = json.dumps(conv)
                json_objs += [json_obj]

            print('spot avail', spot_available4)
            for i in range(len(spot_available4)):
                if spot_available4[i] == 0:
                    cv2.rectangle(img, (boxes4[i][0], boxes4[i][1]),
                                  (boxes4[i][0] + boxes4[i][2], boxes4[i][1] + boxes4[i][3]), (0, 255, 0), 3)
                cv2.putText(img, str(i+1), (boxes4[i][0] + 15, boxes4[i][1] + 40), cv2.FONT_HERSHEY_SIMPLEX, 1,
                            (0, 255, 255), 3)

            cv2.putText(img, 'available spot: {}'.format(len(spot_available4) - sum(spot_available4)), (50, 510),
                        cv2.FONT_HERSHEY_COMPLEX, 1, (255, 0, 0), 3)

            frame = cv2.imencode('.jpg', img)[1].tobytes()
            yield (b'--frame\r\n'b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')


dir_vid5 = 'parking 5.mp4'
loc_spot5 = 'spot space/spot5.pickle'

with(open(loc_spot5, 'rb')) as loc:
    x5 = pickle.load(loc)

spot_available5 = np.zeros(len(x3))

boxes5 = []
for box in x5:
    a, b, c, d = box
    boxes5 += [[int(a / 2), int(b / 2), int(c / 2), int(d / 2)]]


def park5():
    """Video streaming generator function."""
    global z, spot_available5

    status = None
    cap = cv2.VideoCapture(dir_vid5)
    # Read until video is completed
    while cap.isOpened():
        # Capture frame-by-frame
        ret, img = cap.read()
        if not ret:
            cap = cv2.VideoCapture(dir_vid5)



            continue
        if ret:
            img = cv2.resize(img, (0, 0), fx=0.5, fy=0.5)
            z += 1
            if z % 10 == 0:
                status = True

            if status:
                img_crop = []
                for i, box in enumerate(x5):
                    proses1 = img[boxes5[i][1]:boxes5[i][1] + boxes5[i][3], boxes5[i][0]:boxes5[i][0] + boxes5[i][2]]
                    proses2 = cv2.resize(proses1, (50, 50)) / 255.0
                    img_crop += [np.expand_dims(proses2, axis=0)]

                spot_available5 = prediction(img_crop)
                status = False

            json_objs = []
            str_s = ['id', 'value']
            for i in range(len(spot_available5)):
                conv = convert(str_s, [i + 1, bool(spot_available5[i])])
                json_obj = json.dumps(conv)
                json_objs += [json_obj]

            print('spot avail', spot_available5)
            for i in range(len(spot_available5)):
                if spot_available4[i] == 0:
                    cv2.rectangle(img, (boxes5[i][0], boxes5[i][1]),
                                  (boxes5[i][0] + boxes5[i][2], boxes4[i][1] + boxes5[i][3]), (0, 255, 0), 3)
                cv2.putText(img, str(i+1), (boxes5[i][0] + 15, boxes5[i][1] + 40), cv2.FONT_HERSHEY_SIMPLEX, 1,
                            (0, 255, 255), 3)

            cv2.putText(img, 'available spot: {}'.format(len(spot_available5) - sum(spot_available5)), (50, 510),
                        cv2.FONT_HERSHEY_COMPLEX, 1, (255, 0, 0), 3)

            frame = cv2.imencode('.jpg', img)[1].tobytes()
            yield (b'--frame\r\n'b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')


@app.route('/video_parking1')
def video_feed():
    """Video streaming route. Put this in the src attribute of an img tag."""
    return Response(park1(),
                    mimetype='multipart/x-mixed-replace; boundary=frame')


@app.route('/video_parking2')
def video_parking2():
    return Response(park2(), mimetype='multipart/x-mixed-replace; boundary=frame')


@app.route('/video_parking3')
def video_parking3():
    return Response(park3(), mimetype='multipart/x-mixed-replace; boundary=frame')


@app.route('/video_parking4')
def video_parking4():
    """Video streaming route. Put this in the src attribute of an img tag."""
    return Response(park4(),mimetype='multipart/x-mixed-replace; boundary=frame')


@app.route('/video_parking5')
def video_parking5():
    """Video streaming route. Put this in the src attribute of an img tag."""
    return Response(park5(), mimetype='multipart/x-mixed-replace; boundary=frame')


if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0", port=int(os.environ.get("PORT", 8080)))
