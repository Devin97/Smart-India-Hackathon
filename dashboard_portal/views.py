from django.shortcuts import render
from django.http import HttpResponse
import json
import requests

def dashboardPortal(request):
	url = "http://20a770be.ngrok.io/api/singletrainingcenter/"

	data = {
		"center_id":"k1"
	}

	response = requests.post(url, data=data)
	#return HttpResponse(response.text)
	jsonobject = json.loads(response.text)
	print(jsonobject)
	finalresult = jsonobject["data"]
	return render(request, 'pmkvy_test/dashboard.html', {'result':finalresult})
