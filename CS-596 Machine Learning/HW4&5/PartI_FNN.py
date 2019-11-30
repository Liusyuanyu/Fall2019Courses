import tensorflow.compat.v1 as tf
import numpy as np
import scipy
import matplotlib.pyplot as plt
from keras.datasets import mnist
from sklearn.model_selection import train_test_split
from sklearn import preprocessing
from PIL import Image
import util
import my_FNN_Model

def import_data_training():
    # load (downloaded if needed) the MNIST dataset
    (x_train, y_train), (x_test, y_test) = mnist.load_data()
    # transform each image from 28 by28 to a 784 pixel vector
    pixel_count = x_train.shape[1] * x_train.shape[2]
    x_train = x_train.reshape(x_train.shape[0], pixel_count).astype('float32')
    # normalize inputs from gray scale of 0-255 to values between 0-1
    x_train = x_train / 255
    x_train, x_test, y_train, y_test = train_test_split(x_train, y_train, test_size=10000, random_state=34)
    
    #Convert Labels to One hot
    le = preprocessing.LabelEncoder()
    int_encoded = le.fit_transform(y_train)
    oe = preprocessing.OneHotEncoder(categories='auto')
    oe.fit(int_encoded.reshape(len(int_encoded), 1))
    y_train = oe.transform(y_train.reshape(len(y_train),1)).toarray()
    y_test = oe.transform(y_test.reshape(len(y_test),1)).toarray()

    return x_train, y_train, x_test, y_test, oe

def write_to_log(FNN, t):
    FNN.train_writer.add_summary(FNN.summary,t)


    
x_train, y_train, x_test, y_test, oe  = import_data_training()    

training_epoch = 20
# LEARNING_RATE =  0.001
LEARNING_RATE =  0.1
HIDDEN_LAYER_SIZE = 120
# HIDDEN_LAYER_SIZE = 300
HIDDEN_LAYER_NUM = 2
BATCH_SIZE = 100
REGULARIZATION = 0.1
IMAGE_PIXELS = 784
CLASSES = 10

network_params = {'LearningRate':LEARNING_RATE, 'reg':REGULARIZATION, 'HiddenLayerNum':\
                  HIDDEN_LAYER_NUM,'HiddenLayerSize': HIDDEN_LAYER_SIZE}

FNN = my_FNN_Model.FNN_Model(CLASSES, IMAGE_PIXELS, network_params)

for epoch in range(training_epoch):
    avg_cost = 0
    NumberOfInputs, _ = x_train.shape
    total_batch = int(NumberOfInputs / BATCH_SIZE)

    for i in range(total_batch):
        Input_batch = x_train[i*BATCH_SIZE:(i+1)*BATCH_SIZE]
        Output_batch = y_train[i*BATCH_SIZE:(i+1)*BATCH_SIZE]
        cost = FNN.train_step(Input_batch,Output_batch)
        avg_cost += cost/total_batch
    print("Epoch: {}  =====> Cost = {}".format(epoch, avg_cost))
# test model
predicted_output = FNN.predict(x_test, y_test)
correct_prediction = np.equal(np.argmax(predicted_output,1), np.argmax(y_test,1))
# print(correct_prediction)
accuracy = np.sum(correct_prediction.astype(float))/np.size(correct_prediction)
print('Accuracy is {}'.format(accuracy))

y_test_label = [x[0] for x in oe.inverse_transform(y_test)]
y_pred_label = [x[0] for x in oe.inverse_transform(predicted_output)]
conf_matrix, accuracy, recall_array, precision_array = util.func_confusion_matrix(y_test_label,y_pred_label)

wrong_img = np.not_equal(np.argmax(predicted_output,1), np.argmax(y_test,1))
wrong_img = np.where(wrong_img)[0]

fig,ax = plt.subplots(2,5,figsize=(20,10))
fig.subplots_adjust(hspace=0, wspace=0.05)
for count in range(10):
    img = np.copy(x_test[wrong_img[count]])
    img = np.asarray(np.reshape(img,[28,28]))
    img = (img * 255).astype(np.uint8)
    img =Image.fromarray(img, 'L')
    ax[count%2][count//2].imshow(img,cmap='gray')
    ax[count%2][count//2].axis('off')
    title = "Pred: {} -- GT: {}".format(y_pred_label[wrong_img[count]],y_test_label[wrong_img[count]])
    ax[count%2][count//2].set_title(title,fontsize= 25)
plt.show()





