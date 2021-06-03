GOOGLE_PROJECT_ID=b21-cap0192
CLOUD_RUN_SERVICE=capstone-api-firebase

gcloud builds submit --tag gcr.io/$GOOGLE_PROJECT_ID/$CLOUD_RUN_SERVICE \
  --project=$GOOGLE_PROJECT_ID

gcloud run deploy $CLOUD_RUN_SERVICE \
  --image gcr.io/$GOOGLE_PROJECT_ID/$CLOUD_RUN_SERVICE \
  --platform managed \
  --region asia-southeast2 \
  --allow-unauthenticated \
  --project=$GOOGLE_PROJECT_ID