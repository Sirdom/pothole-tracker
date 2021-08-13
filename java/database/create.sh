#!/bin/bash
BASEDIR=$(dirname $0)
DATABASE=final_capstone
psql -U postgres -f "$BASEDIR/dropdb.sql" &&
createdb -U postgres $DATABASE &&
psql -U postgres -d $DATABASE -f "$BASEDIR/schema.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/potholeInfo.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/schedule.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/claims.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/employeeRole.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/user.sql"
