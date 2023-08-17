obj-m += lkm_example.o

all:
	make -C ${KERNEL_BUILD} M=$(PWD) modules

clean:
	make -C ${KERNEL_BUILD} M=$(PWD) clean
