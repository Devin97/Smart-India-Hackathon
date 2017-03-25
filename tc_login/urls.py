from django.conf.urls import url, include
from django.contrib import admin
from . import views

app_name = "trainingcenterlogin"

urlpatterns = [
	url(r'^$', views.loginpage, name='trainingcenterlogin'),
	url(r'^portal/', views.trainingCenterLogin, name='trainingcenterlogin'),
	#url(r'^', include("dashboard_portal.urls")),
]