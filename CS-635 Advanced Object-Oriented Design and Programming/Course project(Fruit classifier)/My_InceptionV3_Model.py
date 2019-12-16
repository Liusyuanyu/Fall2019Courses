import numpy as np
from keras.layers import Dense, Activation, Conv2D, MaxPooling2D, Dropout
from keras.layers import AveragePooling2D, Input, concatenate, BatchNormalization
import keras.losses as losses
import keras.backend as k
from keras.models import Model

def Inception_A(x):
    part1_1x1 = Conv2D(64,(1,1), strides=1,name=None,padding='same')(x)
    
    part2_3x3_1 = Conv2D(48,(1,1), strides=1,name=None,padding='same')(x)
    part2_3x3_1 = BatchNormalization()(part2_3x3_1)
    part2_3x3_1 = Conv2D(64,(3,3), strides=1,name=None,padding='same')(part2_3x3_1)
    part2_3x3_1 = BatchNormalization()(part2_3x3_1)

    part3_3x3_2 = Conv2D(64,(1,1), strides=1,name=None,padding='same')(x)
    part3_3x3_2 = BatchNormalization()(part3_3x3_2)
    part3_3x3_2 = Conv2D(96,(3,3), strides=1,name=None,padding='same')(part3_3x3_2)
    part3_3x3_2 = BatchNormalization()(part3_3x3_2)
    part3_3x3_2 = Conv2D(96,(3,3), strides=1,name=None,padding='same')(part3_3x3_2)
    part3_3x3_2 = BatchNormalization()(part3_3x3_2)

    avgpool = AveragePooling2D(pool_size=3,strides=1,padding='same')(x)
    avgpool = Conv2D(32,(1,1),strides=1,padding='same')(avgpool)
    avgpool = BatchNormalization()(avgpool)

    x = concatenate([part1_1x1, part2_3x3_1, part3_3x3_2, avgpool],axis=3)
    return x

def Grid_Size_Reduction_AToB(x):
    
    branch1_3x3_1 = Conv2D(64,(1,1), strides=1,name=None,padding='same')(x)
    branch1_3x3_1 = BatchNormalization()(branch1_3x3_1)
    branch1_3x3_1 = Conv2D(96,(3,3), strides=1,name=None,padding='same')(branch1_3x3_1)
    branch1_3x3_1 = BatchNormalization()(branch1_3x3_1)
    branch1_3x3_1 = Conv2D(96,(3,3), strides=2,name=None)(branch1_3x3_1)
    branch1_3x3_1 = BatchNormalization()(branch1_3x3_1)

    branch2_3x3_1 = Conv2D(64,(1,1), strides=1,name=None,padding='same')(x)
    branch2_3x3_1 = BatchNormalization()(branch2_3x3_1)
    branch2_3x3_1 = Conv2D(384,(3,3), strides=2,name=None)(branch2_3x3_1)
    branch2_3x3_1 = BatchNormalization()(branch2_3x3_1)

    maxpool = MaxPooling2D(pool_size=(3,3),strides=2, padding='valid')(x)
    
    x = concatenate([branch1_3x3_1,branch2_3x3_1,maxpool],axis=3,name='First_size_reduction')
    return x

def Inception_B(x):
    branch1_1x1 = Conv2D(192,(1,1), strides=1,padding='same')(x)
    branch1_1x1 = BatchNormalization()(branch1_1x1)
    
    branch2_7x7_1 = Conv2D(128,(1,1), strides=1,padding='same')(x)
    branch2_7x7_1 = BatchNormalization()(branch2_7x7_1)
    branch2_7x7_1 = Conv2D(128,(1,7), strides=1,padding='same')(branch2_7x7_1)
    branch2_7x7_1 = BatchNormalization()(branch2_7x7_1)
    branch2_7x7_1 = Conv2D(192,(7,1), strides=1,padding='same')(branch2_7x7_1)
    branch2_7x7_1 = BatchNormalization()(branch2_7x7_1)

    branch3_7x7_2 = Conv2D(128,(1,1), strides=1,padding='same')(x)
    branch3_7x7_2 = BatchNormalization()(branch3_7x7_2)
    branch3_7x7_2 = Conv2D(128,(1,7), strides=1,padding='same')(branch3_7x7_2)
    branch3_7x7_2 = BatchNormalization()(branch3_7x7_2)
    branch3_7x7_2 = Conv2D(128,(7,1), strides=1,padding='same')(branch3_7x7_2)
    branch3_7x7_2 = BatchNormalization()(branch3_7x7_2)
    branch3_7x7_2 = Conv2D(128,(1,7), strides=1,padding='same')(branch3_7x7_2)
    branch3_7x7_2 = BatchNormalization()(branch3_7x7_2)
    branch3_7x7_2 = Conv2D(192,(7,1), strides=1,padding='same')(branch3_7x7_2)
    branch3_7x7_2 = BatchNormalization()(branch3_7x7_2)

    avgpool = AveragePooling2D(pool_size=(3,3),strides=1,padding='same')(x)
    avgpool = Conv2D(192,(1,1),strides=1,padding='same')(avgpool)
    avgpool = BatchNormalization()(avgpool)

    x = concatenate([branch1_1x1,branch2_7x7_1,branch3_7x7_2,avgpool],axis=3)
    return x

def Grid_Size_Reduction_BToC(x):
    
    branch1_3x3 = Conv2D(192,(1,1), strides=1,name=None,padding='same')(x)
    branch1_3x3 = BatchNormalization()(branch1_3x3)
    branch1_3x3 = Conv2D(320,(3,3), strides=2,name=None)(branch1_3x3)
    branch1_3x3 = BatchNormalization()(branch1_3x3)

    branch1_7x7 = Conv2D(192,(1,1), strides=1,name=None,padding='same')(x)
    branch1_7x7 = BatchNormalization()(branch1_7x7)
    branch1_7x7 = Conv2D(192,(1,7), strides=1,name=None,padding='same')(branch1_7x7)
    branch1_7x7 = BatchNormalization()(branch1_7x7)
    branch1_7x7 = Conv2D(192,(7,1), strides=1,name=None,padding='same')(branch1_7x7)
    branch1_7x7 = BatchNormalization()(branch1_7x7)
    branch1_7x7 = Conv2D(192,(3,3), strides=2,name=None)(branch1_7x7)
    branch1_7x7 = BatchNormalization()(branch1_7x7)

    maxpool = MaxPooling2D(pool_size=(3,3),strides=2, padding='valid')(x)
    
    x = concatenate([branch1_3x3,branch1_7x7,maxpool],axis=3,name='Second_size_reduction')
    return x

def Inception_C(x):
    branch1_1x1 = Conv2D(320,(1,1), strides=1,padding='same')(x)
    branch1_1x1 = BatchNormalization()(branch1_1x1)
    
    branch2_3x3 = Conv2D(384,(1,1), strides=1,padding='same')(x)
    branch2_3x3 = BatchNormalization()(branch2_3x3)
    branch2_1x3 = Conv2D(384,(1,3), strides=1,padding='same')(branch2_3x3)
    branch2_3x3 = BatchNormalization()(branch2_3x3)
    branch2_3x1 = Conv2D(384,(3,1), strides=1,padding='same')(branch2_3x3)
    branch2_3x3 = BatchNormalization()(branch2_3x3)

    branch3_3x3 = Conv2D(448,(1,1), strides=1,padding='same')(x)
    branch3_3x3 = BatchNormalization()(branch3_3x3)
    branch3_3x3 = Conv2D(384,(3,3), strides=1,padding='same')(branch3_3x3)
    branch3_3x3 = BatchNormalization()(branch3_3x3)
    branch3_3x3_1x3 = Conv2D(384,(1,3), strides=1,padding='same')(branch3_3x3)
    branch3_3x3_1x3 = BatchNormalization()(branch3_3x3_1x3)
    branch3_3x3_3x1 = Conv2D(384,(3,1), strides=1,padding='same')(branch3_3x3)
    branch3_3x3_1x3 = BatchNormalization()(branch3_3x3_1x3)

    avgpool = AveragePooling2D(pool_size=(3,3),strides=1,padding='same')(x)
    avgpool = Conv2D(192,(1,1),strides=1,padding='same')(avgpool)
    avgpool = BatchNormalization()(avgpool)
    
    x = concatenate([branch1_1x1,branch2_1x3,branch2_3x1,branch3_3x3_1x3, \
                     branch3_3x3_3x1,avgpool],axis=3)
    return x

def auxiliary_classifier(inputs=None, filter_num=128, num_classes=100):
    aux = AveragePooling2D(pool_size=(3,3), strides = 3,padding='valid')(inputs)
    aux = Conv2D(filter_num, (1,1), padding='same',strides=(1,1),name=None)(aux)
    aux = BatchNormalization()(aux)
    aux = Flatten()(aux)
    aux = Dense(units=num_classes,use_bias=False)(aux)
    soft = Activation(activation='softmax')(aux)
    return soft

#Build InceptionV3 model
def InceptionV3(width, height, depth, class_num):
    inpt = Input(shape=(height,width,depth),name='Input_100x100')
    net = Conv2D(32,(3,3),strides=2, name='Conv2D_3x3_S2')(inpt)
    net = Conv2D(32,(3,3),strides=1,name='Conv2D_3x3_S1_1')(net)
    net = Conv2D(64,(3,3),strides=1,padding='same',name='Conv2D_3x3_S1_2')(net)
    net = MaxPooling2D(pool_size=(3,3),strides=2,padding='valid')(net)
    net = Conv2D(80,(3,3),strides=1,padding='same')(net)
    net = Conv2D(192,(3,3),strides=1,padding='same')(net)
    net = MaxPooling2D(pool_size=(3,3),strides=2,padding='valid')(net)
    
    #3xInception_A
    net = Inception_A(net)
    net = Inception_A(net)
    net = Inception_A(net)
    #Grid Size Reduction (A to B)
    net = Grid_Size_Reduction_AToB(net)

    #5xInception_B
    net = Inception_B(net)
    net = Inception_B(net)
    net = Inception_B(net)
    net = Inception_B(net)
    net = Inception_B(net)

    #Auxiliary Classifier
    aux_classifier = auxiliary_classifier(net, num_classes=class_num)

    #Grid Size Reduction (B to C)
    net = Grid_Size_Reduction_BToC(net)
    
    #2xInception_C
    net = Inception_C(net)
    net = Inception_C(net)
    
    #Prediction
    shape = net.get_shape()
    net = AveragePooling2D(pool_size=shape[1:3],strides=1)(net)
    net =Flatten()(net)
    net = Dropout(0.5)(net)
    net = Dense(1024,activation='relu')(net)
    net = Dropout(0.5)(net)
    net = Dense(class_num,activation='softmax')(net)
    
    model=Model(inputs=inpt,outputs=net)
    return model,aux_classifier

def My_cross_entropy_wAux(auxiliary):
    Label_smoothing = 0.2
    aux_rate = 0.4
    def lossFunc(y_true,y_pred):
        ce_loss = losses.categorical_crossentropy(y_true,y_pred,label_smoothing=Label_smoothing)
        aux_loss = losses.categorical_crossentropy(y_true,auxiliary,label_smoothing=Label_smoothing)
        total_loss = aux_loss*(aux_rate) + ce_loss
        return total_loss
    return lossFunc
    #End of My_cross_entropy_LSRnAux

# Compile Model
def BuildInceptionV3Model(train_shape, num_class):
    [height,width,im_depth]=train_shape

    k.clear_session()
    inceptionModel,aux_classifier = InceptionV3(height=height,width=width,depth=im_depth, class_num=num_class)

    inceptionModel.compile(optimizer='RMSprop',
                  loss=My_cross_entropy_wAux(aux_classifier), # Call the loss function with the selected layer
                  metrics=['accuracy'])
    return inceptionModel