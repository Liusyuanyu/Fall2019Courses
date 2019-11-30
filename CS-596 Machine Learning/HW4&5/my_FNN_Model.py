import tensorflow.compat.v1 as tf
import numpy as np
import scipy
import matplotlib.pyplot as plt
from keras.datasets import mnist
from sklearn.model_selection import train_test_split
from sklearn import preprocessing
from PIL import Image
import util

class FNN_Model:
    """
    neural network model
    """
    def __init__(self, classes_num, IMAGE_PIXELS, params={}):
        self.classes_num = classes_num
        self.image_pixels = IMAGE_PIXELS
        self.LEARNING_RATE = params['LearningRate']
        self.reg = params['reg']

        self.num_hidden = params['HiddenLayerNum']
        self.hidden_size = params['HiddenLayerSize']
        tf.disable_eager_execution()
        tf.keras.backend.clear_session()
        self.session = self.create_model()

    def define_placeholders(self):
        images_placeholder = tf.placeholder(tf.float32, shape = (None, self.image_pixels))
        labels_placeholder = tf.placeholder(tf.int64, shape = (None, self.classes_num))
        return images_placeholder, labels_placeholder

    def variable_summaries(self,var, name):
        with tf.name_scope('summaries'):
            mean = tf.reduce_mean(var)
            tf.summary.scalar('mean/' + name, mean)
        with tf.name_scope('stddev'):
            stddev = tf.sqrt(tf.reduce_sum(tf.square(var - mean)))
        tf.summary.scalar('sttdev/' + name, stddev)
        tf.summary.scalar('max/' + name, tf.reduce_max(var))
        tf.summary.scalar('min/' + name, tf.reduce_min(var))

    def multilayer_perceptron(self, input_mat):
        n_input = self.image_pixels
        n_hidden_1 = self.hidden_size
        n_hidden_2 = self.hidden_size
        n_classes = self.classes_num

        weights = {
            'h1': tf.Variable(tf.truncated_normal([n_input, n_hidden_1],stddev = 0.1)),
            'h2': tf.Variable(tf.truncated_normal([n_hidden_1, n_hidden_2],stddev = 0.1)),
            'out': tf.Variable(tf.truncated_normal([n_hidden_2, n_classes],stddev = 0.1))
        }
        biases = {
            'b1': tf.Variable(tf.random_normal([n_hidden_1], stddev = 0.1)),
            'b2': tf.Variable(tf.random_normal([n_hidden_2], stddev = 0.1)),
            'out': tf.Variable(tf.random_normal([n_classes], stddev = 0.1))
        }
        # Hidden layer with RELU activation
        layer_1 = tf.add(tf.matmul(input_mat, weights['h1']), biases['b1'])
        layer_1 = tf.nn.relu(layer_1)
        # Hidden layer with RELU activation
        layer_2 = tf.add(tf.matmul(layer_1, weights['h2']), biases['b2'])
        layer_2 = tf.nn.relu(layer_2)
        # Output layer with linear activation
        out_layer = tf.matmul(layer_2, weights['out']) + biases['out']
        reg_term = self.reg * (tf.reduce_sum(tf.square(weights['h1']))+
        tf.reduce_sum(tf.square(weights['h2']))+tf.reduce_sum(tf.square(weights['out'])))
        return out_layer, reg_term, weights, biases

    def create_model(self):
        self.images_placeholder, self.labels_placeholder = self.define_placeholders()
        output, reg_term, weights, biases = self.multilayer_perceptron(self.images_placeholder)

        self.variable_summaries(weights['h1'], 'layer1'+ '/weights')
        self.variable_summaries(weights['h2'], 'layer2' + '/weights')
        self.variable_summaries(weights['out'], 'layer3' + '/weights')

        self.NN_output = output

        self.cost = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits_v2(logits=output,labels=self.labels_placeholder))

        tf.summary.scalar('cost', self.cost)

        optimizer = tf.train.GradientDescentOptimizer(learning_rate = self.LEARNING_RATE)
        self.train_op = optimizer.minimize(self.cost)
        init = tf.global_variables_initializer()

        session = tf.Session()
        session.run(init)
        self.saver_op = tf.train.Saver()
        self.merged = tf.summary.merge_all()
        return session

    def init_t(self):
        self.t = 0

    def train_step(self, Input_mat, labels):
        _, cost, prediction_probs, self.summary = self.session.run(
        [self.train_op, self.cost, self.NN_output, self.merged],
        feed_dict = {
            self.images_placeholder : Input_mat,
            self.labels_placeholder : labels
        })
        return cost

    def predict(self, states, label):
        cost, prediction_probs = self.session.run(
        [self.cost, self.NN_output],
        feed_dict = {
        self.images_placeholder: states,
        self.labels_placeholder: label
        })
        return prediction_probs