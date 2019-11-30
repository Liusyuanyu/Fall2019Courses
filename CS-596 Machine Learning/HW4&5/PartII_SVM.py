import numpy as np
import matplotlib.pyplot as plt
import sklearn.svm as svm
from sklearn import metrics
import util
import download_data as dl
import pandas as pd
from sklearn.model_selection import train_test_split

## step 1: load data from csv file. 
data = dl.download_data('crab.csv').values

n = 200
#split data 
S = np.random.permutation(n)
#100 training samples
Xtr = data[S[:100], :6]
Ytr = data[S[:100], 6:]
# 100 testing samples
X_test = data[S[100:], :6]
Y_test = data[S[100:], 6:].ravel()


## step 2 randomly split Xtr/Ytr into two even subsets: use one for training, another for validation.
#############placeholder 1: training/validation #######################
n2 = len(Xtr)
S2 = np.random.permutation(n2)

# subsets for training models
x_train= Xtr[S2[:50]]
y_train= Ytr[S2[50:]].ravel()
# subsets for validation
x_validation= Xtr[S2[:50]]
y_validation= Ytr[S2[50:]].ravel()
############placeholder end #######################

# 3.1 Plot the validation errors while using different values of C ( with other hyperparameters fixed) 
#  keeping kernel = "linear"
#############placeholder 2: Figure 1#######################

best_c = -1
lowest_err = 10000
best_kernel = ''

## Linear
c_range =  range(1,20) #For linear
svm_c_error = []
for c_value in c_range:
    model = svm.SVC(kernel='linear', C=c_value)
    model.fit(X=x_train, y=y_train)
    error = 1. - model.score(x_validation, y_validation)
    svm_c_error.append(error)
    if lowest_err > error:
        lowest_err = error
        best_c = c_value
        best_kernel='linear'
    
plt.plot(c_range, svm_c_error)
plt.title('Linear SVM')
plt.xlabel('c values')
plt.ylabel('error')
plt.xticks(c_range)
plt.show()

## Poly
# c_range =  range(1,100,5) # For Poly
svm_c_error = []
for c_value in c_range:
    model = svm.SVC(kernel='poly', C=c_value,gamma='scale')
    model.fit(X=x_train, y=y_train)
    error = 1. - model.score(x_validation, y_validation)
    svm_c_error.append(error)
    if lowest_err > error:
        lowest_err = error
        best_c = c_value
        best_kernel='poly'
    
plt.plot(c_range, svm_c_error)
plt.title('Poly SVM')
plt.xlabel('c values')
plt.ylabel('error')
plt.xticks(c_range)
plt.show()

## RBF
c_range =  range(1,200,10) #For RBF
svm_c_error = []
for c_value in c_range:
    model = svm.SVC(kernel='rbf', C=c_value, gamma='scale')
    model.fit(X=x_train, y=y_train)
    error = 1. - model.score(x_validation, y_validation)
    svm_c_error.append(error)
    if lowest_err > error:
        lowest_err = error
        best_c = c_value
        best_kernel='rbf'
plt.plot(c_range, svm_c_error)
plt.title('RBF SVM')
plt.xlabel('c values')
plt.ylabel('error')
plt.xticks(c_range)
plt.show()

#############placeholder end #######################
# print('C Best : ', best_c)
# 3.2 Plot the validation errors while using linear, RBF kernel, or Polynomial kernel ( with other hyperparameters fixed) 
#############placeholder 3: Figure 2#######################
kernel_types = ['linear', 'poly', 'rbf']
svm_kernel_error = []
c_value = best_c
for kernel_value in kernel_types:
    # your own codes
    model = svm.SVC(kernel=kernel_value, C=c_value,gamma='scale')
    model.fit(X=x_train, y=y_train)
    error = 1. - model.score(x_validation, y_validation)
    svm_kernel_error.append(error)

plt.plot(kernel_types, svm_kernel_error)
plt.title('SVM by Kernels')
plt.xlabel('Kernel')
plt.ylabel('error')
plt.xticks(kernel_types)
plt.show()
#############placeholder end #######################

## step 4 Select the best model and apply it over the testing subset 
#############placeholder 4:testing  #######################

# best_kernel = 'linear'
# best_c = 9 # poly had many that were the "best"
model = svm.SVC(kernel=best_kernel, C=best_c, gamma='scale')
model.fit(X=x_train, y=y_train)

#############placeholder end #######################

## step 5 evaluate your results in terms of accuracy, real, or precision. 

#############placeholder 5: metrics #######################
# func_confusion_matrix is not included
# You might re-use this function for the Part I. 
y_pred = model.predict(X_test)
conf_matrix, accuracy, recall_array, precision_array = util.func_confusion_matrix(Y_test, y_pred)

print("Confusion Matrix: ")
print(conf_matrix)
print("Average Accuracy: {}".format(accuracy))
print("Per-Class Precision: {}".format(precision_array))
print("Per-Class Recall: {}".format(recall_array))

#############placeholder end #######################

#############placeholder 6: success and failure examples #######################
# Success samples: samples for which you model can correctly predict their labels
correct_sample = np.equal(y_pred, Y_test)
correct_sample = np.where(correct_sample)[0]

correct = pd.DataFrame(X_test[correct_sample])
correct['GT'] = Y_test[correct_sample]
correct['Pred'] = y_pred[correct_sample]

print("Success samples:")
print(correct[:5])

# # Failure samples: samples for which you model can not correctly predict their labels
wrong_sample = np.not_equal(y_pred, Y_test)
wrong_sample = np.where(wrong_sample)[0]

wrong = pd.DataFrame(X_test[wrong_sample])
wrong['GT'] = Y_test[wrong_sample]
wrong['Pred'] = y_pred[wrong_sample]

print("Failure samples:")
print(wrong[:5])
#############placeholder end #######################