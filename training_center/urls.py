from django.conf.urls import url
from django.contrib import admin
from . import views

app_name = "trainingcenter"

urlpatterns = [
	url(r'^$', views.trainingCenterpage, name='trainingCenterpage'),
	url(r'^fetch/', views.fetchTrainingCenter, name='getTrainingCenter'),
]