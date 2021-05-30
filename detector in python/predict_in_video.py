import numpy as np
import tensorflow as tf
import pickle
import cv2 as cv

#read location of video
video = cv.VideoCapture('D:/bangkit/ml/tensorflow/projrct final/parking lot 3.mp4')

#add label to predict
labeling = {}
labeling[0]='free'
labeling[1] ='occupied'

#model of training data
train_model= 'D:/bangkit/ml/tensorflow/projrct final/training1.h5'

#load data training
model = tf.keras.models.load_model(train_model)
#location of spot/coordinat
spot_tuple = 'D:/bangkit/ml/tensorflow/projrct final/spot.pickle'

#read koordinat to create location rectangle
with(open(spot_tuple, 'rb')) as file:
    x = pickle.load(file)


def prediction(img):
    #normalise frame video
    image = img/255.0

    img = np.expand_dims(image, axis=0)
    #make model to predik
    predict = model.predict(img)
    id = np.argmax(predict[0])
    label = labeling[id]
    return label
    
while True:
    #read video menjadi per frame
    ret, frame = video.read()
    
    #untuk tempat jumlah parking kosong
    spot_avail = 0
    # copy frame dari video
    hasil = np.copy(frame)
    
    for i, box in enumerate(x):
        a,b,c,d = box 
        #melakukan crop supaya sesuai dengan letak koordinat yang akan diprediksi
        img_crop = frame[b:b+d, a:a+c]
        #dilakukan resize sesuia dengan input di model disesuaikan karena ketika membuat model menggunakan (75, 75)
        img_resize = cv.resize(img_crop, (75,75))

        #melakukan prediksi sesuai dengan frame/frame sesuai load dari model
        label = prediction(img_resize)

        #read hasil prediksi dan read lokasi koordinat
        print(label)
        print(box)

        #jika label menjadi free sesuai label prediksi maka akan create rectangle dan menambahkan jumlah parkir, jika ingin menambhakan rectangle ke letak koordinasi yang occupied/berisis mobil dapat menmbahkan script
        #elif label == 'occupied':
        #    cv.rectangle(hasil, (a,b), (a+c, b+d), (255,255,0), 3)
        if label == 'free':
            cv.rectangle(hasil, (a,b), (a+c, b+d), (0,255,0), 3)#warna hijau
            spot_avail += 1
        
        #manmbahkan tulisan angka di dalam box
        cv.putText(hasil, str(i+1), (a+50, b+50), cv.FONT_HERSHEY_SIMPLEX, 2, (255, 255,0), 4)
    #manambahkan tulisan berapa jumlah spot
    cv.putText(hasil, 'avalaible spot {}'.format(spot_avail), (100,1000), cv.FONT_HERSHEY_SIMPLEX, 1, (255,255,0), 3)

    #cv.imwrite('D:/bangkit/ml/tensorflow/projrct final/result2.jpg', hasil)
    #menampilkan hasil prediksi
    cv.imshow('video parking 1', hasil)
    #untuk melakukan supaya close tanpa menggunakan esc because, can't close with tanda x 
    if cv.waitKey(1) & 0xFF == ord('q'):
        break

cv.destroyAllWindows()
video.release()