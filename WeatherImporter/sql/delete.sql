delete from hff_weather_forecast_detail;

delete from hff_weather_forecast_head;

delete from hff_weather_area where PARENT_AREA ='VIC_ME002';

delete from hff_weather_area where PARENT_AREA ='VIC_FA001';

delete from hff_weather_area;

delete from hff_weather_area_au;

delete from hff_weather_import_stg;

commit;
