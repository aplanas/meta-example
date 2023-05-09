SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Recipe created by bitbake-layers"
DEPENDS = ""
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3da9cfbcb788c80a0384361b4de20420"

python do_display_banner() {
    bb.plain("***********************************************");
    bb.plain("*                                             *");
    bb.plain("*  Example recipe created by bitbake-layers   *");
    bb.plain("*                                             *");
    bb.plain("***********************************************");
}

addtask display_banner before do_build

SRC_URI = "file://hello.c \
           file://LICENSE"

S = "${WORKDIR}"

# libc6 is automatically added
PRIVATE_LIBS = "libc.so.6"
INSANE_SKIP:${PN} += "file-rdeps"

# Do not own "/usr", "/usr/bin".  "None" is representing a non-existen
# directory path.
DIRFILES = "None"

do_compile() {
  ${CC} hello.c -o hello
}

do_install() {
  install -d ${D}${bindir}
  install -m 0755 hello ${D}${bindir}
}
