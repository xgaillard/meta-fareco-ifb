#!/bin/bash

# Force absolute path if not in meta-fareco/scripts
YOCTO_PATH=/home/xavier/axiomtek-hardknott

APPLI_PATH=/home/xavier/CARTES_SIAT/RBOX/workspaceNewWeb

# Absolute path for download and sstate cache if not in yocto directory
DOWNLOAD_PATH=
SSTATE_PATH=

# Check if running from container
if grep -sq 'docker\|lxc' /proc/1/cgroup; then
   	echo "This script should not be launched from a container!"
	exit 1
fi

# Set options for docker
[ -z "$YOCTO_PATH" ] && YOCTO_PATH="$( cd -- "$(dirname "$0")/"/../../.. >/dev/null 2>&1; pwd -P )"
[ -n "$DOWNLOAD_PATH" ] && DOWNLOAD_PATH="-v $DOWNLOAD_PATH:$DOWNLOAD_PATH"
[ -n "$SSTATE_PATH" ] && SSTATE_PATH="-v $SSTATE_PATH:$SSTATE_PATH"

#CONTAINER=fareco-yocto:phytec-dunfell
CONTAINER=c0765528214c
CONTAINER_HOME=/home/pokyuser

# Build and run the container



#echo "\
#FROM crops/poky:ubuntu-20.04

CONTAINER_HOME=/home/pokyuser

# Build and run the container



#echo "\
#FROM crops/poky:ubuntu-20.04
#USER root
#RUN apt install zip bc qpdf -y
#USER usersetup
#" | \
#docker build -t ${CONTAINER} - && \
#

docker run --rm -it \
	-v ${YOCTO_PATH}:${YOCTO_PATH} \
	-v ${APPLI_PATH}:${APPLI_PATH} \
	-v ${HOME}/.ssh:${CONTAINER_HOME}/.ssh \
	${DOWNLOAD_PATH} \
	${SSTATE_PATH} \
	--mount type=bind,source=${HOME}/.gitconfig,target=${CONTAINER_HOME}/.gitconfig \
	--add-host="docinfo:172.20.0.1" \
	--workdir=${YOCTO_PATH} \
	${CONTAINER}

