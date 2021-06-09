This is folder contains python and ipynb files

The preprocessing folder contains the files before processing in the web use flask,
# 1. first process in the preprocessing folder
- `./src/preprocessing/detected_line(box_conture).ipynb` to detect the contour and search for the point, which will be used for the detection system
  use python and openCV
- `./src/preprocessing/split_file.ipynb` then divides the data into train and validation with 80% training and 20% validation, data free and parking lot occupied
- `./src/preprocessing/model_train.ipynb` then the data will be trained using CNN with 3 layers, then saved as train1.h5 (this data will be used in the detection system)
# 2. Then it is necessary to run a detection system because the system is based online, the Flask library is used as a link so that it can be accessed on the web
- `./src/main.py` as detection by using openCv and tensorflow as detector
- `./src/video/ ` this video folder contains 5 parking prototype videos 

# train1.h5 this is a training_model
