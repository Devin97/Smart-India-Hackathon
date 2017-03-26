from django.conf.urls import url, include
from django.contrib import admin
from . import views

app_name = "dashboard"

urlpatterns = [
	url(r'^$', views.centerLoginPage, name='login'),
	#url(r'^portal/', views.trainingCenterLogin, name='trainingcenterlogin'),
	url(r'^dashboard/', views.dashboardView, name='dashboardview'),
	url(r'^dashboard/updated/', views.updateCenterInfo, name='update'),
	url(r'^logout/', views.logout, name='logout'),
]