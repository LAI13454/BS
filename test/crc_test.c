#include <stdio.h>
int main(void){
	unsigned char bit = 0;
	unsigned char crc = 0x01;
	for (bit = 8; bit > 0; --bit)
	{
		if (crc & 0x80)
			crc = (crc << 1) ^ 0x31;
		else
            crc = (crc << 1);
    }
	printf("%02x\n",crc);	

}
