SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Recipe created by bitbake-layers"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

python do_display_banner() {
    bb.plain("***********************************************");
    bb.plain("*                                             *");
    bb.plain("*  Example recipe created by bitbake-layers   *");
    bb.plain("*                                             *");
    bb.plain("***********************************************");
}

addtask display_banner before do_build

SRC_URI = "file://lkm_example.c \
	   file://Makefile \
	   "

S = "${WORKDIR}"

DEPENDS = "kernel-default-devel \
	   kernel-devel \
	   kernel-source \
	   "

# TODO: the kernel needs to be re-compiled, as several tools (like
# fixdep) are now for the TARGET architecture

# TODO: re-use kernel-arch definitions
KERNEL_CC = "${HOST_PREFIX}gcc -fuse-ld=bfd ${DEBUG_PREFIX_MAP} -fdebug-prefix-map=${STAGING_KERNEL_DIR}=${KERNEL_SRC_PATH} -fdebug-prefix-map=${STAGING_KERNEL_BUILDDIR}=${KERNEL_SRC_PATH}"
KERNEL_LD = "${HOST_PREFIX}ld.bfd"
KERNEL_AR = "${HOST_PREFIX}ar"
KERNEL_OBJCOPY = "${HOST_PREFIX}objcopy"


set_variables() {
    export KERNEL_VERSION="$(basename "$(ls -d -1 ${STAGING_BASELIBDIR}/modules/* | sort -nr | head -1)")"
    export KERNEL_BUILD="${STAGING_BASELIBDIR}/modules/$KERNEL_VERSION/build"
}

do_configure:prepend() {
    set_variables
}

do_compile() {
    set_variables
    oe_runmake CC="${KERNEL_CC}" LD="${KERNEL_LD}" \
               AR="${KERNEL_AR}" OBJCOPY="${KERNEL_OBJCOPY}" \
               ARCH="arm64"
}

do_install() {
    set_variables
    install -d ${D}/lib/modules/$KERNEL_VERSION/example
    install -m 0644 lkm_example.ko ${D}/lib/modules/$KERNEL_VERSION/example
}

EXCLUDE_FROM_WORLD = "1"
