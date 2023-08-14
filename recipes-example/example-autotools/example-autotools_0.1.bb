#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#

DESCRIPTION = "Simple helloworld application"
SECTION = "examples"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=96af5705d6f64a88e035781ef00e98a8"

# FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${PV}:"

SRCREV = "5c58a861ca31710f6433487b8b0d590a43afd0ad"
SRC_URI = "git://github.com/DynamicDevices/bbexample.git;branch=master;protocol=https"
SRC_URI[sha256sum] = "b5ed2aa37abb361d3d36464b19f5d60f056440193833d898371fdf4005fadd17"

S = "${WORKDIR}/git"

inherit autotools

# The autotools configuration I am basing this on seems to have a problem with a race condition when parallel make is enabled
# PARALLEL_MAKE = ""
