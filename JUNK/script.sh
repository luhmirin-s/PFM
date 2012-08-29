#!/bin/bash

from=~/git2/PFM/GWT/PFMweb/war
to=~/glassfish/glassfish3/glassfish/domains/domain1/eclipseApps/test

sudo cp -u -r $from $to 
echo "Done at: "
date
