from django.contrib import admin

from .models import TestTable, CustomTest, TrainingCenter
from .models import TrainingCenterCourse, CandidateRegistration, AppUser
from .models import JobRole, SectorSkillCouncil, CourseInfo
from .models import BatchInfo, StudentCourseRegistration, CourseFeedbackDetail
from .models import StatewiseDistrict, StateIndia

admin.site.register(TestTable)
admin.site.register(CustomTest)
admin.site.register(TrainingCenter)
admin.site.register(AppUser)
admin.site.register(CandidateRegistration)
admin.site.register(JobRole)
admin.site.register(SectorSkillCouncil)
admin.site.register(CourseInfo)
admin.site.register(TrainingCenterCourse)
admin.site.register(BatchInfo)
admin.site.register(StudentCourseRegistration)
admin.site.register(CourseFeedbackDetail)
admin.site.register(StateIndia)
admin.site.register(StatewiseDistrict)