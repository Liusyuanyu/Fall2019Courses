import numpy as np
import matplotlib.pyplot as plt
from getDataset import getDataSet
from sklearn.linear_model import LogisticRegression
from codeLogit.util import *
from sklearn.utils.validation import column_or_1d

    
# Starting codes

# Fill in the codes between "%PLACEHOLDER#start" and "PLACEHOLDER#end"

# step 1: generate dataset that includes both positive and negative samples,
# where each sample is described with two features.
# 250 samples in total.

[X, y] = getDataSet()  # note that y contains only 1s and 0s,

# create figure for all charts to be placed on so can be viewed together
fig = plt.figure(figsize=(20, 6))

y = y.ravel()

def func_DisplayData(dataSamplesX, dataSamplesY, chartNum, titleMessage):
    idx1 = (dataSamplesY == 0).nonzero()  # object indices for the 1st class
    idx2 = (dataSamplesY == 1).nonzero()
    ax = fig.add_subplot(1, 3, chartNum)
    # no more variables are needed
    plt.plot(dataSamplesX[idx1, 0], dataSamplesX[idx1, 1], 'r*')
    plt.plot(dataSamplesX[idx2, 0], dataSamplesX[idx2, 1], 'bo')
    # axis tight
    ax.set_xlabel('x_1')
    ax.set_ylabel('x_2')
    ax.set_title(titleMessage)


# plotting all samples
func_DisplayData(X, y, 1, 'All samples')

# number of training samples
nTrain = 120

######################PLACEHOLDER 1#start#########################
# write you own code to randomly pick up nTrain number of samples for training and use the rest for testing.
# WARNIN: 

maxIndex = len(X)
randomTrainingSamples = np.random.choice(maxIndex, nTrain, replace=False)

trainX = X[randomTrainingSamples]   #  training samples
trainY = y[randomTrainingSamples] # labels of training samples 
 
testX =   X[[ind for ind in range(maxIndex) if ind not in randomTrainingSamples] ]# testing samples               
testY =   y[[ind for ind in range(maxIndex) if ind not in randomTrainingSamples] ]# labels of testing samples     nTest X 1

####################PLACEHOLDER 1#end#########################

# plot the samples you have pickup for training, check to confirm that both negative
# and positive samples are included.
func_DisplayData(trainX, trainY, 2, 'training samples')
func_DisplayData(testX, testY, 3, 'testing samples')

# show all charts
plt.show(fig)


#  step 2: train logistic regression models

######################PLACEHOLDER2 #start#########################
# in this placefolder you will need to train a logistic model using the training data: trainX, and trainY.

########################################################################
#########################    Using sklearn    ##########################
########################################################################
clf = LogisticRegression(solver='lbfgs')
clf.fit(trainX,trainY)


########################################################################
###################    Using self-developed model    ###################
########################################################################
theta = [0,0] #initial model parameters
alpha = 0.1 # learning rates
max_iteration = 1000 # maximal iterations
m = len(trainY) # number of samples

for x in range(max_iteration):
    # call the functions for gradient descent method
    new_theta = Gradient_Descent(trainX,trainY,theta,m,alpha)
    theta = new_theta

    
######################PLACEHOLDER2 #end #########################

 
# step 3: Use the model to get class labels of testing samples.
 
######################PLACEHOLDER3 #start#########################
# codes for making prediction, 
# with the learned model, apply the logistic model over testing samples
# hatProb is the probability of belonging to the class 1.
# y = 1/(1+exp(-Xb))
# yHat = 1./(1+exp( -[ones( size(X,1),1 ), X] * bHat )); ));
# WARNING: please DELETE THE FOLLOWING CODEING LINES and write your own codes for making predictions


scikit_yHat = clf.predict(testX)

my_yHat= []
for ind in range(len(testX)):
    prediction = round(Prediction(testX[ind],theta))
    my_yHat += [prediction]


######################PLACEHOLDER 3 #end #########################


# step 4: evaluation
# compare predictions yHat and and true labels testy to calculate average error and standard deviation
scikit_testYDiff = np.abs(scikit_yHat - testY)
avgErr = np.mean(scikit_testYDiff)
stdErr = np.std(scikit_testYDiff)

print('SciKit average error: {} ({})'.format(avgErr, stdErr))

my_testYDiff = np.abs(my_yHat - testY)
avgErr = np.mean(my_testYDiff)
stdErr = np.std(my_testYDiff)

print('My code average error: {} ({})'.format(avgErr, stdErr))