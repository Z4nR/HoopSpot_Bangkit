import pickle
import cv2
from flask import Flask, render_template, Response
import tensorflow as tf
import numpy as np
import json

app = Flask(__name__)


@app.route('/')
def anyname():
    """Video streaming home page."""
    return render_template('index.html')


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
    boxes += [[int(a / 2), int(b / 2), int(c / 2), int(d / 2)]]


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


def convert(a, b):
    zipped = zip(a, b)
    op = dict(zipped)
    return op


def gen():
    """Video streaming generator function."""
    status = None
    global spot_available, z
    cap = cv2.VideoCapture('parking lot 3.mp4')

    # Read until video is completed
    while cap.isOpened():
        # Capture frame-by-frame
        ret, img = cap.read()
        # print(type(ret), 't', type(img))
        # print(type(img), np.shape(img))
        if not ret:
            frame = cv2.VideoCapture('parking lot 3.mp4')
            continue
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
                    proses1 = img[boxes[i][1]:boxes[i][1] + boxes[i][3], boxes[i][0]:boxes[i][0] + boxes[i][2]]
                    proses2 = cv2.resize(proses1, (50, 50)) / 255.0
                    img_crop += [np.expand_dims(proses2, axis=0)]

                print('img crop', len(img_crop))
                spot_available = prediction(img_crop)
                status = False

            str_s = [bool(flt) for flt in spot_available]
            classes = [str(i+1) for i in range(len(spot_available))]
            conv = convert(classes, str_s)
            json_obj = json.dumps(conv)
            print('converted :{}'.format(json_obj))

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
            # time.sleep(0.0000000000000000000000001)
            yield (b'--frame\r\n'b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')
        else:
            break
            #[{'id':'1', 'value': True}, {}]


spot2 = 'spot2.pickle'
with(open(spot2, 'rb')) as loc:
    sp2 = pickle.load(loc)

spot_available2 = np.zeros(len(x))
boxes_2 = []
for box in sp2:
    a, b, c, d = box
    boxes_2 += [[int(a / 2), int(b / 2), int(c / 2), int(d / 2)]]


def park2():
        """Video streaming generator function."""
        status = None
        global spot_available2, z
        cap = cv2.VideoCapture('parking lot 2.mp4')

        # Read until video is completed
        while cap.isOpened():
            # Capture frame-by-frame
            ret, img = cap.read()
            # print(type(ret), 't', type(img))
            # print(type(img), np.shape(img))
            if not ret:
                frame = cv2.VideoCapture('parking lot 2.mp4')
                continue
            if ret:
                img = cv2.resize(img, (0, 0), fx=0.5, fy=0.5)
                # print(type(img), np.shape(img))
                z += 1
                print(z)
                if z % 10 == 0:
                    status = True

                if status:
                    img_crop = []
                    for i, box in enumerate(sp2):
                        proses1 = img[boxes_2[i][1]:boxes_2[i][1] + boxes_2[i][3], boxes_2[i][0]:boxes_2[i][0] + boxes_2[i][2]]
                        proses2 = cv2.resize(proses1, (50, 50)) / 255.0
                        img_crop += [np.expand_dims(proses2, axis=0)]

                    print('img crop', len(img_crop))
                    spot_available2 = prediction(img_crop)
                    status = False

                str_s = [bool(flt) for flt in spot_available2]
                classes = [str(i + 1) for i in range(len(spot_available2))]
                conv = convert(classes, str_s)
                json_obj = json.dumps(conv)
                print('converted :{}'.format(json_obj))

                print('spot avail', spot_available2)
                for i in range(len(spot_available2)):
                    if spot_available2[i] == 0:
                        cv2.rectangle(img, (boxes_2[i][0], boxes_2[i][1]),
                                      (boxes_2[i][0] + boxes_2[i][2], boxes_2[i][1] + boxes_2[i][3]), (0, 255, 0), 3)
                    cv2.putText(img, str(i), (boxes_2[i][0] + 15, boxes_2[i][1] + 40), cv2.FONT_HERSHEY_SIMPLEX, 1,
                                (0, 255, 255), 3)

                cv2.putText(img, 'available spot: {}'.format(len(spot_available2) - sum(spot_available2)), (50, 510),
                            cv2.FONT_HERSHEY_COMPLEX, 1, (255, 0, 0), 3)

                frame = cv2.imencode('.jpg', img)[1].tobytes()
                # time.sleep(0.0000000000000000000000001)
                yield (b'--frame\r\n'b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')
            else:
                break
                # [{'id':'1', 'value': True}, {}]


@app.route('/video_park2')
def video_park2():
    return Response(park2(),
                    mimetype='multipart/x-mixed-replace; boundary=frame')


@app.route('/video_feed')
def video_feed():
    """Video streaming route. Put this in the src attribute of an img tag."""
    return Response(gen(),
                    mimetype='multipart/x-mixed-replace; boundary=frame')

# if _name_ == "_main_":
#    app.run(debug=True, host='192.168.1.138', threaded=True)
