# spring-batch-processor
sample code to test the spring Batch processing framework

#### Sample output

```
2020-12-22 12:00:05.023  INFO 30956 --- [           main] o.s.batch.core.job.SimpleStepHandler     : Executing step: [step1]
2020-12-22 12:00:06.937  INFO 30956 --- [           main] o.s.batch.core.step.AbstractStep         : Step: [step1] executed in 1s914ms
!!! JOB FINISHED! Time to verify the results
**** COUNT **** : 30000
LAST CUSTOMER <CustomerInfoBean{cardNumber='FA53866AF765378C8C2156B551F254B8', mobileNumber='700030000', email='700030000', expDate='null', status='null'}> in the database.
FIRST CUSTOMER <CustomerInfoBean{cardNumber='21AC5E1D9F87F3EA4DACA404AACCA0A7', mobileNumber='700030001', email='700030001', expDate='null', status='null'}> in the database.
2020-12-22 12:00:06.941  INFO 30956 --- [           main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [FlowJob: [name=importUserJob]] completed with the following parameters: [{run.id=1}] and the following status: [COMPLETED] in 1s924ms
```
