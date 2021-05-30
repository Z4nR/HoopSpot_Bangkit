import numpy as np
import tensorflow as tf
import pickle
import os
import cv2 as cv

images = cv.imread('D:/bangkit/ml/tensorflow/projrct final/images/parking_62.jpg')
labeling = {}
labeling[0]='free'
labeling[1] ='occupied'

train_model= 'D:/bangkit/ml/tensorflow/projrct final/training1.h5'
model = tf.keras.models.load_model(train_model)
spot_tuple = 'D:/bangkit/ml/tensorflow/projrct final/spot.pickle'


with(open(spot_tuple, 'rb')) as file:
    x = pickle.load(file)


def prediction(img):
    image = img/255.0

    img = np.expand_dims(image, axis=0)
    predict = model.predict(img)
    id = np.argmax(predict[0])
    label = labeling[id]
    return label
    

def predict_image_parking(img, loc=x, copy_img=True):

    spot_avail = 0
    available = 0

    if copy_img:
        image = np.copy(img)
        hasil = np.copy(img)
    
    for i, box in enumerate(loc):
        a,b,c,d = box 
        img_crop = img[b:b+d, a:a+c]
        img_resize = cv.resize(img_crop, (75,75))
        label = prediction(img_resize)
        print(label)
        print(box)

        if label == 'free':
            cv.rectangle(hasil, (a,b), (a+c, b+d), (0,255,0), 3)
            spot_avail += 1
        #elif label == 'occupied':
        #    cv.rectangle(hasil, (a,b), (c, d), (255,0,0), 3)

        cv.putText(hasil, str(i+1), (a+50, b+50), cv.FONT_HERSHEY_SIMPLEX, 2, (255, 255,0), 4)

    cv.putText(hasil, 'avalaible spot {}'.format(spot_avail), (100,1000), cv.FONT_HERSHEY_SIMPLEX, 1, (255,255,0), 3)

    cv.imwrite('D:/bangkit/ml/tensorflow/projrct final/result2.jpg', hasil)
    return image

result_img = predict_image_parking(images)
