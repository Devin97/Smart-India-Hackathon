from django.shortcuts import render, reverse
from django.http import HttpResponse, HttpResponseRedirect
from api.models import TrainingCenter
import json
import requests

def centerLoginPage(request):     ##login
	return render(request, 'pmkvy_test/login.html')
"""
def trainingCenterLogin(request):          ##dashboard
	
	url = "http://9a3a0b42.ngrok.io/api/singletrainingcenter/"

	center_id = request.POST["centerid"]

	data = {
		"center_id":center_id
	}

	response = requests.post(url, data=data)
	#return HttpResponse(response.text)
	if (response.text == "false") or (response.text == "False") and (request.session.get("center_id", None)):
		return HttpResponseRedirect("/tc_login/")
	else:
		request.session['center_id']=center_id
		jsonobject = json.loads(response.text)

		finalresult = jsonobject["data"]
		return HttpResponseRedirect(reverse("/tc_login/dashboard/", args=(center_id,)))

		#return render(request, 'pmkvy_test/dashboard.html', {'result':finalresult})
"""
def dashboardView(request):

	url = "http://localhost:8000/api/singletrainingcenter/"

	try:
		if request.session.get('center_id'):
			center_id = request.session['center_id']
		else:	
			#return HttpResponseRedirect('/tc_login/')
			center_id = request.POST["centerid"]
	except:
		return HttpResponseRedirect('/tc_login/')		

	data = {
		"center_id":center_id
	}

	response = requests.post(url, data=data)

	jsonobject = json.loads(response.text)
	finalresult = jsonobject["data"]
	if response.text == "false":
		return HttpResponseRedirect("/tc_login/")
	else:
		request.session['center_id'] = center_id	
		if request.session.get('center_id'):
			print("Logged in"+request.session['center_id'])
			"""
			request.session["alldata"]["c_name"] = finalresult["c_name"]
			request.session["alldata"]["c_id"] = finalresult["c_id"]
			request.session["alldata"]["c_addr"] = finalresult["c_addr"]
			request.session["alldata"]["c_partner_name"] = finalresult["c_partner_name"]
			request.session["alldata"]["c_poc_name"] = finalresult["c_poc_name"]
			request.session["alldata"]["c_poc_email"] = finalresult["c_poc_email"]
			"""


			return render(request, 'pmkvy_test/dashboard.html', {'result':finalresult})
		else:
			print("Visitor")
			return HttpResponseRedirect("/tc_login/")

def logout(request):
	del request.session['center_id']
	return HttpResponse(True)

"""
def dashboardPortal(request):
	url = "http://9a3a0b42.ngrok.io/api/singletrainingcenter/"

	center_id = request.session['center_id']
	data = {
		"center_id":center_id
	}

	response = requests.post(url, data=data)
	#return HttpResponse(response.text)
	jsonobject = json.loads(response.text)
	print(jsonobject)
	finalresult = jsonobject["data"]
	request.session['alldata'] = jsonobject
	return render(request, 'pmkvy_test/dashboard.html', {'result':finalresult})
"""
def updateCenterInfo(request):

	if not request.session['alldata']:
		c_name = request.POST["c_name"]
		c_id = request.POST["c_id"]
		c_address = request.POST["c_addr"]
		c_partner_name = request.POST["c_partner_name"]
		c_poc_name = request.POST["c_poc_name"]
		c_poc_email = request.POST["c_poc_email"]
	"""	
	else:
		c_name = request.session["alldata"]["c_name"]
		c_id = request.session["alldata"]["c_id"]
		c_address = request.session["alldata"]["c_addr"]
		c_partner_name = request.session["alldata"]["c_partner_name"]
		c_poc_name = request.session["alldata"]["c_poc_name"]
		c_poc_email = request.session["alldata"]["c_poc_email"]
	"""
	training_center = TrainingCenter.objects.get(center_id=c_id)

	training_center.training_center_name = c_name
	training_center.address = c_address
	#training_center.training_partner = c_partner_name
	training_center.center_poc_name = c_poc_name
	training_center.center_poc_email = c_poc_email
	training_center.save()

	#return HttpResponseRedirect('/tc_login/dashboard/')
	dashboardView(request)
	#return render(request, 'pmkvy_test/dashboard.html', {'updateresult':'Updated Successfully'})





