from django.shortcuts import render
from django.http import HttpResponse
import json
import requests

def trainingCenterpage(request):
	return render(request, 'pmkvy_test/training_center.html')

def fetchTrainingCenter(request):

	url = "http://localhost:8000/api/trainingcenter/"

	finalresult = []

	district = request.POST['district']

	data = {
		"training_center_district" : district
	}

	response = requests.post(url, data=data)
	print(response.text)
	jsonresult = json.loads(response.text)
	try:
		for data in jsonresult["data"]:
			center_id = data["center_id"]
			training_center_name = data["training_center_name"]
			address = data["address"]
			training_center_district = data["training_center_district"]
			training_center_state = data["training_center_state"]
			center_poc_name = data["center_poc_name"]
			center_poc_email = data["center_poc_email"]

			result = {
				'center_id':center_id,
				'training_center_name':training_center_name,
				'address':address,
				'training_center_district':training_center_district,
				'training_center_state':training_center_state,
				'center_poc_name':center_poc_name,
				'center_poc_email':center_poc_email
			}
			finalresult.append(result)
		return render(request, 'pmkvy_test/training_center.html', {'result':finalresult})
	except Exception as e:
		print(str(e))

