# API Analysis (This API is only for TEST)

### Get Config API  (API 1)

```apl
https://TEST_BASE_URL/test_config.json
```

#### API response example

* JSON

```json
{
    "firstAdRatio": "3:1"
}      
                  
```
#### Fields in API response
- `firstAdRatio` Ratio to cross show 'ads' and 'campaigns'.





### Get Ads API  (API 2)

```apl
https://TEST_BASE_URL/test_ads.json
```

#### API response example

* JSON

```json
{
    "campaigns": [
        {
            "id": 1,
            "name": "test campaign 1",
            "imageUrl": "https://s3-ap-northeast-1.amazonaws.com/buzzvi.test/creatives/ad_creative_1.jpg",
            "firstDisplayPriority": 10,
            "firstDisplayWeight": 1,
            "frequency": 1800,
            "landing_url": "http://google.com"
        },
        {
            "id": 2,
            "name": "test campaign 2",
            "imageUrl": "https://s3-ap-northeast-1.amazonaws.com/buzzvi.test/creatives/ad_creative_2.jpg",
            "firstDisplayPriority": 10,
            "firstDisplayWeight": 1,
            "frequency": 900,
            "landing_url": "http://www.naver.com"
        },

        ...

    ]
}      
                  
```
#### Fields in API response
- `id` Unique ID that identifies the campaign.
- `name` Campaign name to display at the bottom of each page showing the campaign.
- `imageUrl` A campaign image to output as the background of each page showing the campaign.
- `firstDisplayPriority` First page campaign impression priority, with higher priority (lower value) campaigns being organized on the first page first.
- `firstDisplayWeight` First page campaign impression weight. With the same priority, the higher the weight (the higher the value), the higher the chance of getting an impression.





### Get Articles API  (API 3)

```apl
https://TEST_BASE_URL/test_articles.json
```

#### API response example

* JSON

```json
{
    "campaigns": [
        {
            "id": 10001,
            "name": "test campaign 1",
            "imageUrl": "https://s3-ap-northeast-1.amazonaws.com/buzzvi.test/creatives/article_creative_1.jpg",
            "firstDisplayPriority": 10,
            "firstDisplayWeight": 1,
            "frequency": 1800,
            "landing_url": "http://google.com"
        },
        {
            "id": 10002,
            "name": "test campaign 2",
            "imageUrl": "https://s3-ap-northeast-1.amazonaws.com/buzzvi.test/creatives/article_creative_2.jpg",
            "firstDisplayPriority": 10,
            "firstDisplayWeight": 1,
            "frequency": 900,
            "landing_url": "http://www.naver.com"
        },

        ...
        
    ]
}   
                  
```
#### Fields in API response
- `id` Unique ID that identifies the campaign.
- `name` Campaign name to display at the bottom of each page showing the campaign.
- `imageUrl` A campaign image to output as the background of each page showing the campaign.
- `firstDisplayPriority` First page campaign impression priority, with higher priority (lower value) campaigns being organized on the first page first.
- `firstDisplayWeight` First page campaign impression weight. With the same priority, the higher the weight (the higher the value), the higher the chance of getting an impression.










