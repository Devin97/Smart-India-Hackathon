"""pmkvy_test URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.9/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url, include
from django.contrib import admin
from rest_framework.urlpatterns import format_suffix_patterns
from api import views 

urlpatterns = [
    url(r'^admin/', admin.site.urls),
    #url(r'^api/customtest/', views.CustomTestList.as_view(), name='api'),
    url(r'^api/trainingcenter/', views.TrainingCenterList.as_view(), name='api_trainingcenter'),
    url(r'^api/candidates/', views.CandidateList.as_view(), name='api_candidate_list'),
    url(r'^api/users/', views.AppUserView.as_view(), name='app_user_view'),
    #url(r'^api/users/(?P<pk>[0-9]+)/$', views.AppUserView.as_view(), name='app_user_view'),
    url(r'^api/logincheck/', views.LoginCheck.as_view(), name='login_check'),
    url(r'^api/singletrainingcenter', views.SingleTrainingCenter.as_view(), name='single_training_center'),
    url(r'^api/jobroledata', views.JobRoleData.as_view(), name='api_jobrole'),
    url(r'^api/coursedata/', views.CourseData.as_view(), name='api_coursedata'),
    url(r'^api/batchinfolist/', views.BatchInfoList.as_view(), name='api_batchinfolist'),
    url(r'^api/fetchtrainingcentercourse/', views.FetchTrainingCenterCourse.as_view(), name='api_fetchtrainingcentercourse'),
    url(r'^api/batchinfocourse/', views.BatchInfoCourse.as_view(), name='api_batchinfocourse'),
    url(r'^trainingcenter/', include("training_center.urls")),
]

urlpatterns = format_suffix_patterns(urlpatterns)