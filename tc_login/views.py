from django.shortcuts import render
from django.http import HttpResponse, Http404
from dashboard_portal import views as portal
import json
import requests

def loginpage(request):
	return render(request, 'pmkvy_test/login.html')

def trainingCenterLogin(request):

	email = request.POST["email"]
	password = request.POST["password"]

	data = {
		"user_email":email,
		"user_password":password
	}

	url = "http://20a770be.ngrok.io/api/logincheck/"

	response = requests.post(url, data=data)

	if str(response.text) == "True" or str(response.text) == "true":
		return portal.dashboardPortal(request)
	else:
		raise Http404	
