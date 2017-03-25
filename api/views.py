from django.shortcuts import render, get_object_or_404
from django.http import JsonResponse, Http404
import json

from .models import CustomTest, TrainingCenter, TrainingCenterCourse
from .models import CandidateRegistration, AppUser, JobRole, SectorSkillCouncil 
from .models import CourseInfo, BatchInfo

from .serializers import TrainingCenterCourseSerializer, CustomTestSerializer 
from .serializers import CourseInfoSerializer, TrainingCenterSerializer, LoginCheckSerializer
from .serializers import CandidateRegistrationSerializer, AppUserSerializer, JobRoleSerializer
from .serializers import BatchInfoSerializer

from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.parsers import JSONParser
from rest_framework import status

##############################################################
# api/customtest/
class CustomTestList(APIView):

	def get(self, request):

		customtests = CustomTest.objects.all()
		serializer = CustomTestSerializer(customtests, many=True)
		return Response(serializer.data)
##############################################################

# api/trainingcenter/
# Takes training center district as a request parameter 
# and returns all the training centers based on the district

class TrainingCenterList(APIView):
	
	def post(self, request, format=None):
		#jsonobject = json.loads(request.body)
		#training_center_district = jsonobject["training_center_district"]
		training_center_district = request.data["training_center_district"]
		trainingcenters = TrainingCenter.objects.filter(training_center_district=training_center_district)	
		serializer = TrainingCenterSerializer(trainingcenters, many=True)
		return Response({'data':serializer.data})

##############################################################

# api/singletrainingcenter
# Takes Center Id as a request parameter and returns the details of the 
# training center based on the center id

class SingleTrainingCenter(APIView):

	def post(self, request, format=None):
		print(request.body)
		jsonobject = json.loads(request.body)
		print(jsonobject)
		center_id = jsonobject["center_id"]
		#center_id = request.data["center_id"]
		try:
			trainingcenter = TrainingCenter.objects.get(center_id=center_id)
			serializer = TrainingCenterSerializer(trainingcenter)
			return Response({'data':serializer.data})
		except:
			return Response(False)

##############################################################

# api/candidates/
# get() -- returns the details of all the candidates
# post() -- Takes App user Email as a request parameter 
	# and returns the details of the user based on the User Email 
	# to check if the "user registrations status" is "True" or "False"

"""
Candidate information Lists and registraion 
"""
class CandidateList(APIView):

	def get(self, request):
		candidates = CandidateRegistration.objects.all()
		serializer = CandidateRegistrationSerializer(candidates, many=True)
		return Response({'candidate_list': serializer.data})

	def post(self, request, format=None):
		c_app_user_email = request.data["c_app_user_email"]
		candidate = CandidateRegistration.objects.get(c_app_user_email=c_app_user_email)
		serializer = CandidateRegistrationSerializer(candidate)
		return Response(serializer.data)

##############################################################

# api/candidates/
# Takes the whole candidate registration credentials as request parameter
# and registers a candidate 
# also sets the "user registrations status" by using app user email

class CandidateRegister(APIView):

	def post(self, request, format=None):
		candidatedata = request.data
		c_app_user_email = request.data["c_app_user_email"]
		serializer = CandidateRegistrationSerializer(data=candidatedata)
		if serializer.is_valid():
			app_user_obj = AppUser.objects.get(user_email=c_app_user_email)
			app_user_obj.user_registration_status = True
			app_user_obj.save()
			instance = serializer.save()
			print(True)
			return Response(True)
		return Response(False)

##############################################################

# api/batchinfolist/
# returns all the batch details		

class BatchInfoList(APIView):

	def get(self, request, format=None):
		batchlist = BatchInfo.objects.all()
		serializer = BatchInfoSerializer(batchlist, many=True)
		return Response({'data':serializer.data})

##############################################################

# api/batchinfocourse/
# Takes Training Center Id and Course Id as a request parameter 
# and returns the batch details based on the parameters		

class BatchInfoCourse(APIView):

	def post(self, request, format=None):
		training_center_id = request.data["training_center_id"]
		course_id = request.data["course_id"]
		courseobj = CourseInfo.objects.get(course_id=course_id)
		center_id = TrainingCenter.objects.get(center_id=training_center_id)
		batchlist = courseobj.batchinfo_set.filter(training_center_id=center_id.id)
		serializer = BatchInfoSerializer(batchlist, many=True)
		return Response(serializer.data)

##############################################################

# api/users/
# get() -- returns the details of all the users
# post() -- Takes all the user registrations credentials as a request parameter 
	# and returns "True" if registrations is successful or "False" if Not

"""
	Login Singup credentials - register, login check
"""

class AppUserView(APIView):
    """
    List all snippets, or create a new snippet.
    """
    def get(self, request, format=None):
    	user = AppUser.objects.all()
    	serializer = AppUserSerializer(user, many=True)
    	return Response({'data':serializer.data})

    def post(self, request, format=None):
    		jsonobject = json.loads(request.body)
	    	print(request.body)
	    	print(jsonobject)
	    	#jsonobject1 = json.loads(jsonobject)
	    	#print(jsonobject1)
	    	serializer = AppUserSerializer(data=jsonobject)
	    	if serializer.is_valid():
	    		instance = serializer.save()
	    		print(True)
	    		return Response(True, status=status.HTTP_201_CREATED)
	    	return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

##############################################################	   

# api/logincheck/
# Takes User Email and User Password as a request parameter 
# and uses it for authentication purpose

class LoginCheck(APIView):

	def post(self, request, format=None):
		jsonobject = json.loads(request.body)
		print(request.body)
		print(jsonobject)
		user_email = jsonobject["user_email"]
		user_password = jsonobject["user_password"]
		try:
			user = AppUser.objects.get(user_email=user_email)
			if user.user_email==user_email and user.user_password==user_password:
				return Response(True, status=status.HTTP_201_CREATED)	
			return Response(False, status=status.HTTP_400_BAD_REQUEST)
		except:
			return Response(False, status=status.HTTP_400_BAD_REQUEST)

##############################################################

# api/jobroledata/
# returns all the job roles			

"""
JobRole, Course and Batch API's
"""
class JobRoleData(APIView):
	
	def get(self, request, format=None):
		jobroles = JobRole.objects.all()
		serializer = JobRoleSerializer(jobroles, many=True)
		return Response({'data':serializer.data})			

##############################################################

# api/coursedata/
# get() -- returns details of all the courses
# post() -- Takes Job Role name as a request parameter 
	# and returns all the course list based on the parameter

class CourseData(APIView):
	
	def get(self, request, format=None):
		courselist = CourseInfo.objects.all()
		serializer = CourseInfoSerializer(courselist, many=True)
		return Response({'data':serializer.data})	

	def post(self, request, format=None):
		#jsonobject = json.loads(request.body)
		#jobrolekey = jsonobject["job_role_name"]
		jobrolekey = request.data["job_role_name"]
		jobroleobj = JobRole.objects.get(job_role_name=jobrolekey)
		courselist = jobroleobj.courseinfo_set.all()
		serializer = CourseInfoSerializer(courselist, many=True)
		return Response(serializer.data)

##############################################################

# api/fetchtrainingcentercourse/
# Takes Training Center Id as a request parameter
# and returns all the courses provided by 		

class FetchTrainingCenterCourse(APIView):

	def post(self, request, format=None):
		training_center_id = request.data["training_center_id"]
		t_id = TrainingCenter.objects.get(center_id=training_center_id)
		datalist = TrainingCenterCourse.objects.filter(training_center_id=t_id)
		serializer = TrainingCenterCourseSerializer(datalist, many=True)
		return Response({'data':serializer.data})
		
##############################################################		