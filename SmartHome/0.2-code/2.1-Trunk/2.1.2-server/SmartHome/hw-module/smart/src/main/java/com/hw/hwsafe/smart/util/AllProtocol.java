package com.hw.hwsafe.smart.util;


public class AllProtocol
{
    final byte P_8_ATM = (byte) 0x07;
    final byte P_8_CCITT = (byte) 0x8D;
    ///* Maxim: reflected 31 */
    final byte P_8_MAXIM = (byte) 0x8C;
    final byte P_8 = (byte) 0xD5;
    final byte P_8_J1850 = (byte) 0x1D;
    ///* WCDMA: reflected 9B */
    final byte P_8_WCDMA = (byte) 0xD9;
    ///* ROHC: reflected 07 */
    final byte P_8_ROHC = (byte) 0xE0;
    ///* DARC: reflected 39 */
    final byte P_8_DARC = (byte) 0x9C;
 
    final short P_16_NORMAL = (short) 0x8005;
    /* 16: 8005 reflected */
    final short P_16_REFLECTED = (short) 0xA001;
 
    final short P_CCITT = (short) 0x1021;
 
    /* DNP: 3D65 reflected */
    final short P_DNP = (short) 0xA6BC;
    final short P_EN_13757 = (short) 0x3D65;
 
    /* Kermit: 1021 reflected */
    final short P_KERMIT = (short) 0x8408;
    final short P_SICK = (short) 0x8005;
 
    final short P_T10_DIF = (short) 0x8BB7;
    final short P_DECT = (short) 0x0589;
    final short P_TELEDISK = (short) 0xA097;
 
    final int P_24 = (int) 0x5D6DCB;
    final int P_24_R64 = (int) 0x864CFB;
 
    final int P_32_NORMAL = (int) 0x04C11DB7;
    /* 32: 04C11DB7 reflected */
    final int P_32_REFLECTED = (int) 0xEDB88320;
    /* C: 1EDC6F41 reflected */
    final int P_32C = (int) 0x82F63B78;
 
    /* D: A833982B reflected */
    final int P_32D = (int) 0xD419CC15;
    final int P_32K = (int) 0x741B8CD7;
    final int P_32Q = (int) 0x814141AB;
 
    final int P_32_XFER = (int) 0x000000AF;
 
    final long P_40_GSM = 0x0004820009;
    final long P_64_NORMAL = (long) 0x42F0E1EBA9EA3693L;
 
    /* 1B:    000000000000001B reflected */
    final long P_64_1B_REFL = (long) 0xD800000000000000L;
    /* Jones: AD93D23594C935A9 reflected */
    final long P_64_JONES_REFL = (long) 0x95AC9329AC4BC9B5L;
 
    private static byte[] crc8_table_atm = new byte[256];
    private static byte[] crc8_table_ccitt = new byte[256];
    private static byte[] crc8_table_maxim = new byte[256];
    private static byte[] crc8_table = new byte[256];
    private static byte[] crc8_table_j1850 = new byte[256];
    private static byte[] crc8_table_wcdma = new byte[256];
    private static byte[] crc8_table_rohc = new byte[256];
    private static byte[] crc8_table_darc = new byte[256];
 
    private static long[] crc_tab641b_reflected = new long[256];
    private static long[] crc_tab64jones_reflected = new long[256];
    private static long[] crc_tab64_normal = new long[256];
    private static long[] crc_tab40gsm_normal = new long[256];
    private static int[] crc_tab32_normal = new int[256];
    private static int[] crc_tab32_reflected = new int[256];
    private static int[] crc_tabxfer_normal = new int[256];
    private static int[] crc_tab32C = new int[256];
    private static int[] crc_tab32D = new int[256];
    private static int[] crc_tab32K = new int[256];
    private static int[] crc_tab32Q = new int[256];
    private static int[] crc_tab24 = new int[256];
    private static int[] crc_tab24r64 = new int[256];
    private static short[] crc_tab_8005_normal = new short[256];
    private static short[] crc_tab_8005_reflected = new short[256];
    private static short[] crc_tab_1021_normal = new short[256];
    private static short[] crc_tab_1021_reflected = new short[256];
    private static short[] crc_tab_3d65_normal = new short[256];
    private static short[] crc_tab_3d65_reflected = new short[256];
    private static short[] crc_tabt10dif = new short[256];
    private static short[] crc_tabdect = new short[256];
    private static short[] crc_tabteledisk = new short[256];
 
    public byte[] Reflect8 =
    {
        (byte) 0x00,(byte) 0x80,(byte) 0x40,(byte) 0xC0,(byte) 0x20,(byte) 0xA0,(byte) 0x60,(byte) 0xE0,(byte) 0x10,(byte) 0x90,(byte) 0x50,(byte) 0xD0,(byte) 0x30,(byte) 0xB0,(byte) 0x70,(byte) 0xF0,
        (byte) 0x08,(byte) 0x88,(byte) 0x48,(byte) 0xC8,(byte) 0x28,(byte) 0xA8,(byte) 0x68,(byte) 0xE8,(byte) 0x18,(byte) 0x98,(byte) 0x58,(byte) 0xD8,(byte) 0x38,(byte) 0xB8,(byte) 0x78,(byte) 0xF8,
        (byte) 0x04,(byte) 0x84,(byte) 0x44,(byte) 0xC4,(byte) 0x24,(byte) 0xA4,(byte) 0x64,(byte) 0xE4,(byte) 0x14,(byte) 0x94,(byte) 0x54,(byte) 0xD4,(byte) 0x34,(byte) 0xB4,(byte) 0x74,(byte) 0xF4,
        (byte) 0x0C,(byte) 0x8C,(byte) 0x4C,(byte) 0xCC,(byte) 0x2C,(byte) 0xAC,(byte) 0x6C,(byte) 0xEC,(byte) 0x1C,(byte) 0x9C,(byte) 0x5C,(byte) 0xDC,(byte) 0x3C,(byte) 0xBC,(byte) 0x7C,(byte) 0xFC,
        (byte) 0x02,(byte) 0x82,(byte) 0x42,(byte) 0xC2,(byte) 0x22,(byte) 0xA2,(byte) 0x62,(byte) 0xE2,(byte) 0x12,(byte) 0x92,(byte) 0x52,(byte) 0xD2,(byte) 0x32,(byte) 0xB2,(byte) 0x72,(byte) 0xF2,
        (byte) 0x0A,(byte) 0x8A,(byte) 0x4A,(byte) 0xCA,(byte) 0x2A,(byte) 0xAA,(byte) 0x6A,(byte) 0xEA,(byte) 0x1A,(byte) 0x9A,(byte) 0x5A,(byte) 0xDA,(byte) 0x3A,(byte) 0xBA,(byte) 0x7A,(byte) 0xFA,
        (byte) 0x06,(byte) 0x86,(byte) 0x46,(byte) 0xC6,(byte) 0x26,(byte) 0xA6,(byte) 0x66,(byte) 0xE6,(byte) 0x16,(byte) 0x96,(byte) 0x56,(byte) 0xD6,(byte) 0x36,(byte) 0xB6,(byte) 0x76,(byte) 0xF6,
        (byte) 0x0E,(byte) 0x8E,(byte) 0x4E,(byte) 0xCE,(byte) 0x2E,(byte) 0xAE,(byte) 0x6E,(byte) 0xEE,(byte) 0x1E,(byte) 0x9E,(byte) 0x5E,(byte) 0xDE,(byte) 0x3E,(byte) 0xBE,(byte) 0x7E,(byte) 0xFE,
        (byte) 0x01,(byte) 0x81,(byte) 0x41,(byte) 0xC1,(byte) 0x21,(byte) 0xA1,(byte) 0x61,(byte) 0xE1,(byte) 0x11,(byte) 0x91,(byte) 0x51,(byte) 0xD1,(byte) 0x31,(byte) 0xB1,(byte) 0x71,(byte) 0xF1,
        (byte) 0x09,(byte) 0x89,(byte) 0x49,(byte) 0xC9,(byte) 0x29,(byte) 0xA9,(byte) 0x69,(byte) 0xE9,(byte) 0x19,(byte) 0x99,(byte) 0x59,(byte) 0xD9,(byte) 0x39,(byte) 0xB9,(byte) 0x79,(byte) 0xF9,
        (byte) 0x05,(byte) 0x85,(byte) 0x45,(byte) 0xC5,(byte) 0x25,(byte) 0xA5,(byte) 0x65,(byte) 0xE5,(byte) 0x15,(byte) 0x95,(byte) 0x55,(byte) 0xD5,(byte) 0x35,(byte) 0xB5,(byte) 0x75,(byte) 0xF5,
        (byte) 0x0D,(byte) 0x8D,(byte) 0x4D,(byte) 0xCD,(byte) 0x2D,(byte) 0xAD,(byte) 0x6D,(byte) 0xED,(byte) 0x1D,(byte) 0x9D,(byte) 0x5D,(byte) 0xDD,(byte) 0x3D,(byte) 0xBD,(byte) 0x7D,(byte) 0xFD,
        (byte) 0x03,(byte) 0x83,(byte) 0x43,(byte) 0xC3,(byte) 0x23,(byte) 0xA3,(byte) 0x63,(byte) 0xE3,(byte) 0x13,(byte) 0x93,(byte) 0x53,(byte) 0xD3,(byte) 0x33,(byte) 0xB3,(byte) 0x73,(byte) 0xF3,
        (byte) 0x0B,(byte) 0x8B,(byte) 0x4B,(byte) 0xCB,(byte) 0x2B,(byte) 0xAB,(byte) 0x6B,(byte) 0xEB,(byte) 0x1B,(byte) 0x9B,(byte) 0x5B,(byte) 0xDB,(byte) 0x3B,(byte) 0xBB,(byte) 0x7B,(byte) 0xFB,
        (byte) 0x07,(byte) 0x87,(byte) 0x47,(byte) 0xC7,(byte) 0x27,(byte) 0xA7,(byte) 0x67,(byte) 0xE7,(byte) 0x17,(byte) 0x97,(byte) 0x57,(byte) 0xD7,(byte) 0x37,(byte) 0xB7,(byte) 0x77,(byte) 0xF7,
        (byte) 0x0F,(byte) 0x8F,(byte) 0x4F,(byte) 0xCF,(byte) 0x2F,(byte) 0xAF,(byte) 0x6F,(byte) 0xEF,(byte) 0x1F,(byte) 0x9F,(byte) 0x5F,(byte) 0xDF,(byte) 0x3F,(byte) 0xBF,(byte) 0x7F,(byte) 0xFF,
    };
 
    /* Common routines for calculations */
    short reverse_endian(short i)
    {
        return (short)((i >> 8) & 0xff | ((i & 0xff) << 8));
    }
 
    /* Unsigned versions of right shift */
    byte rshiftu8(byte value,int nb)
    {
        return (byte)((value >> nb) & ~(0x80 >> (nb-1)) & 0xff);
    }
 
    short rshiftu16(short value,int nb)
    {
        return (short)((value >> nb) & ~(((short) 0x8000) >> (nb-1)));
    }
 
    int rshiftu32(int value,int nb)
    {
        return (value >> nb) & ~(((int) 0x80000000) >> (nb-1));
    }
 
    long rshiftu64(long value,int nb)
    {
        return (long)((value >> nb) & ~(((long) 0x8000000000000000L) >> (nb-1)));
    }
 
    short Reflect16(short Value16)
    {
        return (short)(((Reflect8[Value16 & 0xFF]) << 8) | Reflect8[(Value16 >> 8) & 0xFF]);
    }
 
    int Reflect24(int Value24)
    {
        return (
                (((int)Reflect8[Value24 & 0xFF]) << 16) |
                (((int)Reflect8[(Value24 >> 8) & 0xFF]) << 8) |
                ((int)Reflect8[(Value24 >> 16) & 0xFF])
                );
    }
 
    int Reflect32(int Value32)
    {
        return (
                (((int)Reflect8[Value32 & 0xFF]) << 24) |
                (((int)Reflect8[(Value32 >> 8) & 0xFF]) << 16) |
                (((int)Reflect8[(Value32 >> 16) & 0xFF]) << 8) |
                ((int)Reflect8[(Value32 >> 24) & 0xFF])
                );
    }
 
    long Reflect40(long Value40)
    {
        return (
                (((long)Reflect8[(int)(Value40 & 0xFFL)]) << 32) |
                (((long)Reflect8[(int)((Value40 >> 8) & 0xFFL)]) << 24) |
                (((long)Reflect8[(int)((Value40 >> 16) & 0xFFL)]) << 16) |
                (((long)Reflect8[(int)((Value40 >> 24) & 0xFFL)]) << 8) |
                ((long)Reflect8[(int)((Value40 >> 32) & 0xFFL)])
                );
    }
 
    long Reflect64(long Value64)
    {
        return (
                (((long)Reflect8[(int)(Value64 & 0xFFL)]) << 56) |
                (((long)Reflect8[(int)((Value64 >> 8) & 0xFFL)]) << 48) |
                (((long)Reflect8[(int)((Value64 >> 16) & 0xFFL)]) << 40) |
                (((long)Reflect8[(int)((Value64 >> 24) & 0xFFL)]) << 32) |
                (((long)Reflect8[(int)((Value64 >> 32) & 0xFFL)]) << 24) |
                (((long)Reflect8[(int)((Value64 >> 40) & 0xFFL)]) << 16) |
                (((long)Reflect8[(int)((Value64 >> 48) & 0xFFL)]) << 8) |
                ((long)Reflect8[(int)((Value64 >> 56) & 0xFFL)])
                );
    }
 
    void init_crc8_normal_tab(byte[] table, byte polynom)
    {
        int i, j;
        byte crc8;
 
        for (i=0; i<256; i++)
            {
                crc8 = (byte) i;
 
                for (j=0; j<8; j++)
                    {
                        if ((crc8 & 0x80) != 0)
                            crc8 = (byte)((crc8 << 1) ^ polynom);
                        else
                            crc8 <<= 1;
                    }
                table[i] = crc8;
            }
    }
 
    void init_crc8_reflected_tab(byte[] table, byte polynom)
    {
        int i, j;
        byte crc8;
 
        for (i=0; i<256; i++)
            {
                crc8 = (byte) i;
 
                for (j=0; j<8; j++)
                    {
                        if ((crc8 & 0x01) != 0)
                            crc8 = (byte)(rshiftu8(crc8,1) ^ polynom);
                        else
                            crc8 = rshiftu8(crc8,1);
                    }
                table[i] = crc8;
            }
    }
 
    void init_crc16_normal_tab(short[] table, short polynom)
    {
        int i, j;
        short crc16;
 
        for (i = 0; i < 256; i++)
            {
                crc16 = (short)(i << 8);
 
                for (j = 0; j < 8; j++)
                    {
                        if ((crc16 & 0x8000) != 0)
                            crc16 = (short)((crc16 << 1) ^ polynom);
                        else
                            crc16 <<= 1;
                    }
                table[i] = crc16;
            }
    }
 
    void init_crc16_reflected_tab(short[] table, short polynom)
    {
        int i, j;
        short crc16;
 
        for (i = 0; i < 256; i++)
            {
                crc16 = (short)i;
 
                for (j = 0; j < 8; j++)
                    {
                        if ((crc16 & 0x0001) != 0)
                            crc16 = (short)(rshiftu16(crc16,1) ^ polynom);
                        else
                            crc16 = rshiftu16(crc16,1);
                    }
                table[i] = crc16;
            }
    }
 
    void init_crc24_normal_tab(int[] table, int polynom)
    {
        int i, j;
        int crc24;
 
        for (i = 0; i < 256; i++)
            {
                crc24 = i << 16;
 
                for (j = 0; j < 8; j++)
                    {
                        if ((crc24 & 0x00800000) != 0)
                            crc24 = (crc24 << 1) ^ polynom;
                        else
                            crc24 <<= 1;
                    }
                table[i] = crc24;
            }
    }
 
    void init_crc32_normal_tab(int[] table, int polynom)
    {
        int i, j;
        int crc32;
 
        for (i = 0; i < 256; i++)
            {
                crc32 = i << 24;
 
                for (j = 0; j < 8; j++)
                    {
                        if ((crc32 & 0x80000000) != 0)
                            crc32 = (crc32 << 1) ^ polynom;
                        else
                            crc32 <<= 1;
                    }
                table[i] = crc32;
            }
    }
 
    void init_crc32_reflected_tab(int[] table, int polynom)
    {
        int i, j;
        int crc32;
 
        for (i = 0; i < 256; i++)
            {
                crc32 = i;
 
                for (j = 0; j < 8; j++)
                    {
                        if ((crc32 & 0x00000001) != 0)
                            crc32 = rshiftu32(crc32,1) ^ polynom;
                        else
                            crc32 = rshiftu32(crc32,1);
                    }
                table[i] = crc32;
            }
    }
 
    void init_crc40_normal_tab(long[] table, long polynom)
    {
        int i,j;
        long crc40;
 
        for (i = 0; i < 256; i++)
            {
                crc40 = (long) i << 32;
 
                for (j = 0; j < 8; j++)
                    {
                        if ((crc40 & 0x0000008000000000L) != 0)
                            crc40 = (crc40 << 1) ^ polynom;
                        else
                            crc40 <<= 1;
                    }
                table[i] = crc40;
            }
    }
 
    void init_crc64_normal_tab(long[] table, long polynom)
    {
        int i,j;
        long crc64;
 
        for (i = 0; i < 256; i++)
            {
                crc64 = (long) i << 56;
 
                for (j = 0; j < 8; j++)
                    {
                        if ((crc64 & 0x8000000000000000L) != 0)
                            crc64 = (crc64 << 1) ^ polynom;
                        else
                            crc64 <<= 1;
                    }
                table[i] = crc64;
            }
    }
 
    void init_crc64_reflected_tab(long[] table, long polynom)
    {
        int i,j;
        long crc64;
 
        for (i = 0; i < 256; i++)
            {
                crc64 = (long) i;
 
                for (j = 0; j < 8; j++)
                    {
                        if ((crc64 & 0x0000000000000001L) != 0)
                            crc64 = rshiftu64(crc64,1) ^ polynom;
                        else
                            crc64 = rshiftu64(crc64,1);
                    }
                table[i] = crc64;
            }
    }
 
    public static void print_table8(byte[] table)
    {
        for (int i=0; i<256; i++)
            {
                System.out.printf("%02X ",table[i]);
                if ((i & 0x0f)== 0x0f) System.out.printf("\n");
            }
    }
 
    public void init_all_tab()
    {
        init_crc8_normal_tab(crc8_table_atm, P_8_ATM);
        init_crc8_normal_tab(crc8_table_ccitt, P_8_CCITT);
        init_crc8_reflected_tab(crc8_table_maxim, P_8_MAXIM);
        init_crc8_normal_tab(crc8_table, P_8);
        init_crc8_normal_tab(crc8_table_j1850, P_8_J1850);
        init_crc8_reflected_tab(crc8_table_wcdma, P_8_WCDMA);
        init_crc8_reflected_tab(crc8_table_rohc, P_8_ROHC);
        init_crc8_reflected_tab(crc8_table_darc, P_8_DARC);
        init_crc16_normal_tab(crc_tab_1021_normal, P_CCITT);
        init_crc16_reflected_tab(crc_tab_1021_reflected, P_KERMIT);
        init_crc16_normal_tab(crc_tab_8005_normal, P_16_NORMAL);
        init_crc16_reflected_tab(crc_tab_8005_reflected, P_16_REFLECTED);
        init_crc16_normal_tab(crc_tab_3d65_normal, (short) 0x3D65);
        init_crc16_reflected_tab(crc_tab_3d65_reflected, P_DNP);
        init_crc16_normal_tab(crc_tabt10dif, P_T10_DIF);
        init_crc16_normal_tab(crc_tabdect, P_DECT);
        init_crc16_normal_tab(crc_tabteledisk, P_TELEDISK);
 
        init_crc24_normal_tab(crc_tab24, P_24);
        init_crc24_normal_tab(crc_tab24r64, P_24_R64);
 
        init_crc32_reflected_tab(crc_tab32_reflected, P_32_REFLECTED);
        init_crc32_normal_tab(crc_tab32_normal, P_32_NORMAL);
        init_crc32_normal_tab(crc_tabxfer_normal, P_32_XFER);
        init_crc32_reflected_tab(crc_tab32C, P_32C);
        init_crc32_reflected_tab(crc_tab32D, P_32D);
        init_crc32_normal_tab(crc_tab32K, P_32K); /* Not sure */
        init_crc32_normal_tab(crc_tab32Q, P_32Q);
 
        init_crc40_normal_tab(crc_tab40gsm_normal, P_40_GSM);
        init_crc64_normal_tab(crc_tab64_normal, P_64_NORMAL);
        init_crc64_reflected_tab(crc_tab641b_reflected, P_64_1B_REFL);
        init_crc64_reflected_tab(crc_tab64jones_reflected, P_64_JONES_REFL);
    }
 
    void init_crc8_atm_tab()
    {
        init_crc8_normal_tab(crc8_table_atm, P_8_ATM);
    }
 
    void init_crc8_ccitt_tab()
    {
        init_crc8_normal_tab(crc8_table_ccitt, P_8_CCITT);
    }
 
    void init_crc8_maxim_tab()
    {
        init_crc8_reflected_tab(crc8_table_maxim, P_8_MAXIM);
    }
 
    void init_crc8_tab()
    {
        init_crc8_normal_tab(crc8_table, P_8);
    }
 
    void init_crc8_j1850_tab()
    {
        init_crc8_normal_tab(crc8_table_j1850, P_8_J1850);
    }
 
    void init_crc8_wcdma_tab()
    {
        init_crc8_reflected_tab(crc8_table_wcdma, P_8_WCDMA);
    }
 
    void init_crc8_rohc_tab()
    {
        init_crc8_reflected_tab(crc8_table_rohc, P_8_ROHC);
    }
 
    void init_crc8_darc_tab()
    {
        init_crc8_reflected_tab(crc8_table_darc, P_8_DARC);
    }
 
 
    byte update_crc8_atm( byte crc, byte c )
    {
        return (crc8_table_atm[(int)((crc ^ c) & 0xFF)]);
    }
 
    byte update_crc8_ccitt( byte crc, byte c )
    {
        return (crc8_table_ccitt[(int)((crc ^ c) & 0xFF)]);
    }
 
    byte update_crc8_maxim( byte crc, byte c )
    {
        return (crc8_table_maxim[(int)((crc ^ c) & 0xFF)]);
    }
 
    byte update_crc8( byte crc, byte c )
    {
        return (crc8_table[(int)((crc ^ c) & 0xFF)]);
    }
 
    byte update_crc8_j1850( byte crc, byte c )
    {
        return (crc8_table_j1850[(int)((crc ^ c) & 0xFF)]);
    }
 
    byte update_crc8_wcdma( byte crc, byte c )
    {
        return (crc8_table_wcdma[(int)((crc ^ c) & 0xFF)]);
    }
 
    byte update_crc8_rohc( byte crc, byte c )
    {
        return (crc8_table_rohc[(int)((crc ^ c) & 0xFF)]);
    }
 
    byte update_crc8_darc( byte crc, byte c )
    {
        return (crc8_table_darc[(int)((crc ^ c) & 0xFF)]);
    }
 
    byte calculate_crc8_itu(byte[] p, int length)
    {
        byte crc8;
        int i;
 
        crc8 = 0;
 
        for (i=0; i < length; i++)
            {
                crc8 = update_crc8_atm(crc8,p[i]);
            }
        return (byte)(crc8 ^ 0x55);
    }
 
    byte calculate_crc8_atm(byte[] p, int length)
    {
        byte crc8;
        int i;
 
        crc8 = 0;
 
        for (i=0; i < length; i++)
            {
                crc8 = update_crc8_atm(crc8,p[i]);
            }
        return crc8;
    }
 
    byte calculate_crc8_ccitt(byte[] p, int length)
    {
        byte crc8;
        int i;
 
        crc8 = 0;
 
        for (i=0; i < length; i++)
            {
                crc8 = update_crc8_ccitt(crc8,p[i]);
            }
        return crc8;
    }
 
    byte calculate_crc8_maxim(byte[] p, int length)
    {
        byte crc8;
        int i;
 
        crc8 = 0;
 
        for (i=0; i < length; i++)
            {
                crc8 = update_crc8_maxim(crc8,p[i]);
            }
        return crc8;
    }
 
    byte calculate_crc8(byte[] p, int length)
    {
        byte crc8;
        int i;
 
        crc8 = 0;
 
        for (i=0; i < length; i++)
            {
                crc8 = update_crc8(crc8,p[i]);
            }
        return crc8;
    }
 
    byte calculate_crc8_icode(byte[] p, int length)
    {
        byte crc8;
        int i;
 
        crc8 = (byte)0xFD;
 
        for (i=0; i < length; i++)
            {
                crc8 = update_crc8_j1850(crc8,p[i]);
            }
        return crc8;
    }
 
    byte calculate_crc8_j1850(byte[] p, int length)
    {
        byte crc8;
        int i;
 
        crc8 = (byte)0xFF;
 
        for (i=0; i < length; i++)
            {
                crc8 = update_crc8_j1850(crc8,p[i]);
            }
        return (byte)(~crc8);
    }
 
    byte calculate_crc8_wcdma(byte[] p, int length)
    {
        byte crc8;
        int i;
 
        crc8 = 0;
 
        for (i=0; i < length; i++)
            {
                crc8 = update_crc8_wcdma(crc8,p[i]);
            }
        return crc8;
    }
 
    byte calculate_crc8_rohc(byte[] p, int length)
    {
        byte crc8;
        int i;
 
        crc8 = (byte)0xFF;
 
        for (i=0; i < length; i++)
            {
                crc8 = update_crc8_rohc(crc8,p[i]);
            }
        return crc8;
    }
 
    byte calculate_crc8_darc(byte[] p, int length)
    {
        byte crc8;
        int i;
 
        crc8 = 0;
 
        for (i=0; i < length; i++)
            {
                crc8 = update_crc8_darc(crc8,p[i]);
            }
        return crc8;
    }
 
    short update_crc16_normal(short[] table, short crc, short c )
    {
        short short_c;
 
        short_c  = (short)(0x00ff & c);
 
        return (short)((crc << 8) ^ table[rshiftu16(crc,8) ^ short_c]);
    }
 
    short update_crc16_reflected(short[] table, short crc, short c )
    {
        short short_c;
 
        short_c  = (short)(0x00ff & c);
 
        return (short)(rshiftu16(crc,8) ^ table[(crc ^ short_c) & 0xff]);
    }
 
    int update_crc24_normal(int[] table, int crc, byte c )
    {
        int long_c;
 
        long_c = 0x000000ff & (int) c;
 
        return ((crc << 8) ^ table[(rshiftu32(crc,16) ^ long_c) & 0xff]) & 0x00ffffff;
    }
 
    int update_crc32_normal(int[] table, int crc, byte c )
    {
        int long_c;
 
        long_c = 0x000000ff & (int) c;
 
        return (crc << 8) ^ table[(rshiftu32(crc,24) ^ long_c) & 0xff];
    }
 
    int update_crc32_reflected(int[] table, int crc, byte c )
    {
        int long_c;
 
        long_c = 0x000000ff & (int) c;
 
        return rshiftu32(crc,8) ^ table[(crc ^ long_c) & 0xff];
    }
 
    long update_crc40_normal(long[] table, long crc, byte c )
    {
        long long64_c;
 
        long64_c = 0x00000000000000ffL & (long) c;
 
        return ((crc << 8) ^ table[(int)((rshiftu64(crc,32) ^ long64_c) & (long)0xffL)]) & (long)0x000000ffffffffffL;
    }
 
    long update_crc64_normal(long[] table, long crc, byte c )
    {
        long long64_c;
 
        long64_c = 0x00000000000000ffL & (long) c;
 
        return (crc << 8) ^ table[(int)(((crc >>> 56) ^ long64_c) & (long)0xffL)];
    }
 
    long update_crc64_reflected(long[] table, long crc, byte c )
    {
        long long64_c;
 
        long64_c = 0x00000000000000ffL & (long) c;
 
        return rshiftu64(crc,8) ^ table[(int)((crc ^ long64_c) & (long)0xffL)];
    }
 
    short update_crc_sick( short crc, short c, short prev_byte )
    {
        short short_c, short_p;
 
        short_c = c;
        short_p = (short)(prev_byte << 8);
 
        if ((crc & (short) 0x8000) != 0)
            crc = (short)(((crc << 1) ^ P_SICK) & (short) 0xffff);
        else
            crc = (short)((crc << 1) & (short) 0xffff);
 
        crc ^= (short)(short_c | short_p);
 
        return crc;
    }
 
    short update_crc16_8005( short crc, short c )
    {
        return update_crc16_normal(crc_tab_8005_normal,crc,c);
    }
    short update_crc16_A001( short crc, short c )
    {
        return update_crc16_reflected(crc_tab_8005_reflected,crc,c);
    }
    short update_crc16_1021( short crc, short c )
    {
        return update_crc16_normal(crc_tab_1021_normal,crc,c);
    }
    short update_crc16_8408( short crc, short c )
    {
        return update_crc16_reflected(crc_tab_1021_reflected,crc,c);
    }
    short update_crc16_3D65( short crc, short c )
    {
        return update_crc16_normal(crc_tab_3d65_normal,crc,c);
    }
    short update_crc16_dnp( short crc, short c )
    {
        return update_crc16_reflected(crc_tab_3d65_reflected,crc,c);
    }
    short update_crc16_t10_dif( short crc, short c )
    {
        return update_crc16_normal(crc_tabt10dif,crc,c);
    }
    short update_crc16_0589( short crc, short c )
    {
        return update_crc16_normal(crc_tabdect,crc,c);
    }
    short update_crc16_teledisk( short crc, short c )
    {
        return update_crc16_normal(crc_tabteledisk,crc,c);
    }
    int update_crc24( int crc, byte c )
    {
        return update_crc24_normal(crc_tab24,crc,c);
    }
    int update_crc24_r64( int crc, byte c )
    {
        return update_crc24_normal(crc_tab24r64,crc,c);
    }
    int update_crc32_refl( int crc, byte c )
    {
        return update_crc32_reflected(crc_tab32_reflected,crc,c);
    }
    int update_crc32_norm( int crc, byte c )
    {
        return update_crc32_normal(crc_tab32_normal,crc,c);
    }
    int update_crc32_xfer( int crc, byte c )
    {
        return update_crc32_normal(crc_tabxfer_normal,crc,c);
    }
    int update_crc32_c( int crc, byte c )
    {
        return update_crc32_reflected(crc_tab32C,crc,c);
    }
    int update_crc32_d( int crc, byte c )
    {
        return update_crc32_reflected(crc_tab32D,crc,c);
    }
    int update_crc32_k( int crc, byte c )
    {
        return update_crc32_normal(crc_tab32K,crc,c);
    }
    int update_crc32_q( int crc, byte c )
    {
        return update_crc32_normal(crc_tab32Q,crc,c);
    }
 
    long update_crc40_gsm(long crc, byte c)
    {
        return update_crc40_normal(crc_tab40gsm_normal,crc,c);
    }
 
    long update_crc64(long crc, byte c)
    {
        return update_crc64_normal(crc_tab64_normal,crc,c);
    }
 
    long update_crc64_1B(long crc, byte c)
    {
        return update_crc64_reflected(crc_tab641b_reflected,crc,c);
    }
 
    long update_crc64_jones(long crc, byte c)
    {
        return update_crc64_reflected(crc_tab64jones_reflected,crc,c);
    }
 
    short calculate_crc16_Buypass(short[] p, int length)
    {
        short crc;
        int i;
 
        crc = 0;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc16_8005(crc,p[i]);
            }
        return crc;
    }
 
    short calculate_crc16_DDS_110(short[] p, int length)
    {
        short crc;
        int i;
 
        crc = (short) 0x800D;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc16_8005(crc,p[i]);
            }
        return crc;
    }
 
    short calculate_crc16_EN_13757(short[] p, int length)
    {
        short crc;
        int i;
 
        crc = 0;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc16_3D65(crc,p[i]);
            }
        return (short)(~crc);
    }
 
 
    short calculate_crc16_Teledisk(short[] p, int length)
    {
        short crc;
        int i;
 
        crc = 0;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc16_teledisk(crc,p[i]);
            }
        return crc;
    }
 
    short calculate_crc16(short[] p, int length)
    {
        short crc;
        int i;
 
        crc = 0;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc16_A001(crc,p[i]);
            }
        return crc;
    }
 
    short calculate_crc16_Modbus(short[] p, int length)
    {
        short crc;
        int i;
 
        crc = (short) 0xFFFF;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc16_A001(crc,p[i]);
            }
        return crc;
    }
 
    short calculate_crc16_Maxim(short[] p, int length)
    {
        short crc;
        int i;
 
        crc = 0;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc16_A001(crc,p[i]);
            }
        return (short)(~crc);
    }
 
    short calculate_crc16_USB(short[] p, int length)
    {
        short crc;
        int i;
 
        crc = (short) 0xFFFF;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc16_A001(crc,p[i]);
            }
        return (short)(~crc);
    }
 
    short calculate_crc16_T10_DIF(short[] p, int length)
    {
        short crc;
        int i;
 
        crc = 0;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc16_t10_dif(crc,p[i]);
            }
        return crc;
    }
 
    short calculate_crc16_Dect_X(short[] p, int length)
    {
        short crc;
        int i;
 
        crc = 0;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc16_0589(crc,p[i]);
            }
        return crc;
    }
 
    short calculate_crc16_Dect_R(short[] p, int length)
    {
        short crc;
        int i;
 
        crc = 0;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc16_0589(crc,p[i]);
            }
        return (short)(crc ^ 0x0001);
    }
 
    short calculate_crc16_sick(short[] p, int length)
    {
        short crc;
        int i;
        short Prev_Byte = 0;
 
        crc = 0;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc_sick(crc,p[i],Prev_Byte);
                Prev_Byte = p[i];
            }
 
        return reverse_endian(crc);
    }
 
    short calculate_crc16_DNP(short[] p, int length)
    {
        short crc;
        int i;
 
        crc = 0;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc16_dnp(crc,p[i]);
            }
        crc = (short)(~crc);
 
        return reverse_endian(crc);
    }
 
    short calculate_crc16_Ccitt_Xmodem(short[] p, int length)
    {
        short crc;
        int i;
 
        crc = 0;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc16_1021(crc,p[i]);
            }
        return crc;
    }
 
    short calculate_crc16_Ccitt_FFFF(short[] p, int length)
    {
        short crc;
        int i;
 
        crc = (short) 0xFFFF;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc16_1021(crc,p[i]);
            }
        return crc;
    }
 
    short calculate_crc16_Ccitt_1D0F(short[] p, int length)
    {
        short crc;
        int i;
 
        crc = 0x1D0F;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc16_1021(crc,p[i]);
            }
        return crc;
    }
 
    short calculate_crc16_Genibus(short[] p, int length)
    {
        short crc;
        int i;
 
        crc = (short) 0xFFFF;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc16_1021(crc,p[i]);
            }
        return (short)(~crc);
    }
 
    short calculate_crc16_Kermit(short[] p, int length)
    {
        short crc;
        int i;
 
        crc = 0;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc16_8408(crc,p[i]);
            }
 
        return reverse_endian(crc);
    }
 
    short calculate_crc16_X25(short[] p, int length)
    {
        short crc;
        int i;
 
        crc = (short) 0xFFFF;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc16_8408(crc,p[i]);
            }
        return (short)(~crc);
    }
 
    short calculate_crc16_MCRF4XX(short[] p, int length)
    {
        short crc;
        int i;
 
        crc = (short) 0xFFFF;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc16_8408(crc,p[i]);
            }
        return crc;
    }
 
    short calculate_crc16_Riello(short[] p, int length)
    {
        short crc;
        int i;
 
        crc = 0x554D;
 
        for (i=0; i < length; i++)
            {
                crc = update_crc16_8408(crc,p[i]);
            }
        return crc;
    }
 
    short calculate_chk16_Fletcher(short[] p, int length)
    {
        short check, check_fletcher;
        int i;
 
        check = 0;
        check_fletcher = 0;
 
        for (i=0; i < length; i++)
            {
                check += (p[i]);
                check_fletcher += check;
            }
        return (short)(((check_fletcher & 0xFF) << 8) | (check & 0xFF));
    }
 
    int calculate_crc24_flexray_a(byte[] p, int length)
    {
        int crc32;
        int i;
 
        crc32 = 0x00FEDCBA;
 
        for (i=0; i < length; i++)
            {
                crc32 = update_crc24(crc32,p[i]);
            }
 
        return crc32;
    }
 
    int calculate_crc24_flexray_b(byte[] p, int length)
    {
        int crc32;
        int i;
 
        crc32 = 0x00ABCDEF;
 
        for (i=0; i < length; i++)
            {
                crc32 = update_crc24(crc32,p[i]);
            }
 
        return crc32;
    }
 
    int calculate_crc24_r64(byte[] p, int length)
    {
        int crc32;
        int i;
 
        crc32 = 0x00B704CE;
 
        for (i=0; i < length; i++)
            {
                crc32 = update_crc24_r64(crc32,p[i]);
            }
 
        return crc32;
    }
 
    int calculate_crc32(byte[] p, int length)
    {
        int crc32;
        int i;
 
        crc32 = 0xFFFFFFFF;
 
        for (i=0; i < length; i++)
            {
                crc32 = update_crc32_refl(crc32,p[i]);
            }
 
        return ~crc32;
    }
 
    int calculate_crc32_jamcrc(byte[] p, int length)
    {
        int crc32;
        int i;
 
        crc32 = 0xFFFFFFFF;
 
        for (i=0; i < length; i++)
            {
                crc32 = update_crc32_refl(crc32,p[i]);
            }
 
        return crc32;
    }
 
    int calculate_crc32_c(byte[] p, int length)
    {
        int crc32;
        int i;
 
        crc32 = 0xFFFFFFFF;
 
        for (i=0; i < length; i++)
            {
                crc32 = update_crc32_c(crc32,p[i]);
            }
 
        return ~crc32;
    }
 
    int calculate_crc32_d(byte[] p, int length)
    {
        int crc32;
        int i;
 
        crc32 = 0xFFFFFFFF;
 
        for (i=0; i < length; i++)
            {
                crc32 = update_crc32_d(crc32,p[i]);
            }
 
        return ~crc32;
    }
 
    int calculate_crc32_bzip2(byte[] p, int length)
    {
        int crc32;
        int i;
 
        crc32 = 0xFFFFFFFF;
 
        for (i=0; i < length; i++)
            {
                crc32 = update_crc32_norm(crc32,p[i]);
            }
 
        return ~crc32;
    }
 
    int calculate_crc32_mpeg2(byte[] p, int length)
    {
        int crc32;
        int i;
 
        crc32 = 0xFFFFFFFF;
 
        for (i=0; i < length; i++)
            {
                crc32 = update_crc32_norm(crc32,p[i]);
            }
 
        return crc32;
    }
 
    int calculate_crc32_posix(byte[] p, int length)
    {
        int crc32;
        int i;
 
        crc32 = 0;
 
        for (i=0; i < length; i++)
            {
                crc32 = update_crc32_norm(crc32,p[i]);
            }
 
        return ~crc32;
    }
 
    int calculate_crc32_k(byte[] p, int length)
    {
        int crc32;
        int i;
 
        crc32 = 0;
 
        for (i=0; i < length; i++)
            {
                crc32 = update_crc32_k(crc32,p[i]);
            }
 
        return crc32;
    }
 
    int calculate_crc32_q(byte[] p, int length)
    {
        int crc32;
        int i;
 
        crc32 = 0;
 
        for (i=0; i < length; i++)
            {
                crc32 = update_crc32_q(crc32,p[i]);
            }
 
        return crc32;
    }
 
    int calculate_crc32_xfer(byte[] p, int length)
    {
        int crc32;
        int i;
 
        crc32 = 0;
 
        for (i=0; i < length; i++)
            {
                crc32 = update_crc32_xfer(crc32,p[i]);
            }
 
        return crc32;
    }
 
    long calculate_crc40_gsm(byte[] p, int length)
    {
        long crc64;
        int i;
 
        crc64 = 0L;
 
        for (i=0; i < length; i++)
            {
                crc64 = update_crc40_gsm(crc64,p[i]);
            }
 
        return crc64;
    }
 
    long calculate_crc64(byte[] p, int length)
    {
        long crc64;
        int i;
 
        crc64 = 0L;
 
        for (i=0; i < length; i++)
            {
                crc64 = update_crc64(crc64,p[i]);
            }
 
        return crc64;
    }
 
    long calculate_crc64_1b(byte[] p, int length)
    {
        long crc64;
        int i;
 
        crc64 = 0L;
 
        for (i=0; i < length; i++)
            {
                crc64 = update_crc64_1B(crc64,p[i]);
            }
 
        return crc64;
    }
 
    long calculate_crc64_we(byte[] p, int length)
    {
        long crc64;
        int i;
 
        crc64 = 0xFFFFFFFFFFFFFFFFL;
 
        for (i=0; i < length; i++)
            {
                crc64 = update_crc64(crc64,p[i]);
            }
 
        return ~crc64;
    }
 
    long calculate_crc64_jones(byte[] p, int length)
    {
        long crc64;
        int i;
 
        crc64 = 0xFFFFFFFFFFFFFFFFL;
 
        for (i=0; i < length; i++)
            {
                crc64 = update_crc64_jones(crc64,p[i]);
            }
 
        return crc64;
    }
 
    public static void main(String[] args)
    {
        short[] TestString = {0xEC,0xE1,0xB3,0xD7};
        AllProtocol myCrc = new AllProtocol();
 
        myCrc.init_all_tab();
      
 
        System.out.printf("Test CRC in Java\n\n");
 
        System.out.printf("Test CRC8 algorithms\n\n");
 
//        System.out.printf("Test ITU:       %02X\n",myCrc.calculate_crc8_itu(TestString, 9));
//        System.out.printf("Test ATM:       %02X\n",myCrc.calculate_crc8_atm(TestString, 9));
//        System.out.printf("Test CCITT:     %02X\n",myCrc.calculate_crc8_ccitt(TestString, 9));
//        System.out.printf("Test Maxim:     %02X\n",myCrc.calculate_crc8_maxim(TestString, 9));
//        System.out.printf("Test CRC8:      %02X\n",myCrc.calculate_crc8(TestString, 9));
//        System.out.printf("Test Icode:     %02X\n",myCrc.calculate_crc8_icode(TestString, 9));
//        System.out.printf("Test J1850:     %02X\n",myCrc.calculate_crc8_j1850(TestString, 9));
//        System.out.printf("Test WCDMA:     %02X\n",myCrc.calculate_crc8_wcdma(TestString, 9));
//        System.out.printf("Test ROHC:      %02X\n",myCrc.calculate_crc8_rohc(TestString, 9));
//        System.out.printf("Test DARC:      %02X\n",myCrc.calculate_crc8_darc(TestString, 9));
 
        System.out.printf("\nTest CRC16 algorithms\n");
        
        System.out.printf("Test Buypass:   %04X\n",myCrc.calculate_crc16_Buypass(TestString, TestString.length));
        System.out.printf("Test DDS 110:   %04X\n",myCrc.calculate_crc16_DDS_110(TestString, TestString.length));
        System.out.printf("Test EN 13757:  %04X\n",myCrc.calculate_crc16_EN_13757(TestString, TestString.length));
        System.out.printf("Test Teledisk:  %04X\n",myCrc.calculate_crc16_Teledisk(TestString, TestString.length));
        System.out.printf("Test CRC16:     %04X\n",myCrc.calculate_crc16(TestString, TestString.length));
        System.out.printf("Test Modbus:    %04X\n",myCrc.calculate_crc16_Modbus(TestString, TestString.length));
        System.out.printf("Test Maxim:     %04X\n",myCrc.calculate_crc16_Maxim(TestString, TestString.length));
        System.out.printf("Test USB:       %04X\n",myCrc.calculate_crc16_USB(TestString, TestString.length));
        System.out.printf("Test T10 DIF:   %04X\n",myCrc.calculate_crc16_T10_DIF(TestString, TestString.length));
        System.out.printf("Test Dect X:    %04X\n",myCrc.calculate_crc16_Dect_X(TestString, TestString.length));
        System.out.printf("Test Dect R:    %04X\n",myCrc.calculate_crc16_Dect_R(TestString, TestString.length));
        System.out.printf("Test Sick:      %04X\n",myCrc.calculate_crc16_sick(TestString, TestString.length));
        System.out.printf("Test DNP:       %04X\n",myCrc.calculate_crc16_DNP(TestString, TestString.length));
        System.out.printf("Test XModem:    %04X\n",myCrc.calculate_crc16_Ccitt_Xmodem(TestString, TestString.length));
        System.out.printf("Test FFFF:      %04X\n",myCrc.calculate_crc16_Ccitt_FFFF(TestString, TestString.length));
        System.out.printf("Test 1D0F:      %04X\n",myCrc.calculate_crc16_Ccitt_1D0F(TestString, TestString.length));
        System.out.printf("Test Genibus:   %04X\n",myCrc.calculate_crc16_Genibus(TestString, TestString.length));
        System.out.printf("Test Kermit:    %04X\n",myCrc.calculate_crc16_Kermit(TestString, TestString.length));
        System.out.printf("Test X25:       %04X\n",myCrc.calculate_crc16_X25(TestString, TestString.length));
        System.out.printf("Test MCRF4XX:   %04X\n",myCrc.calculate_crc16_MCRF4XX(TestString, TestString.length));
        System.out.printf("Test Riello:    %04X\n",myCrc.calculate_crc16_Riello(TestString, TestString.length));
        System.out.printf("Test Fletcher:  %04X\n",myCrc.calculate_chk16_Fletcher(TestString, TestString.length));
 
        System.out.printf("\nTest CRC24-64 algorithms\n");
        
        System.out.printf("Test XModem:    %04X\n",myCrc.calculate_crc16_Ccitt_Xmodem(TestString, TestString.length));
 
//        System.out.printf("Test Flexray A: %06X\n",myCrc.calculate_crc24_flexray_a(TestString, 9));
//        System.out.printf("Test Flexray B: %06X\n",myCrc.calculate_crc24_flexray_b(TestString, 9));
//        System.out.printf("Test R64:       %06X\n\n",myCrc.calculate_crc24_r64(TestString, 9));
//        System.out.printf("Test CRC32:     %08X\n",myCrc.calculate_crc32(TestString, 9));
//        System.out.printf("Test JamCRC:    %08X\n",myCrc.calculate_crc32_jamcrc(TestString, 9));
//        System.out.printf("Test CRC32 C:   %08X\n",myCrc.calculate_crc32_c(TestString, 9));
//        System.out.printf("Test CRC32 D:   %08X\n",myCrc.calculate_crc32_d(TestString, 9));
//        System.out.printf("Test BZIP2:     %08X\n",myCrc.calculate_crc32_bzip2(TestString, 9));
//        System.out.printf("Test MPEG2:     %08X\n",myCrc.calculate_crc32_mpeg2(TestString, 9));
//        System.out.printf("Test Posix:     %08X\n",myCrc.calculate_crc32_posix(TestString, 9));
//        System.out.printf("Test CRC32 K:   %08X\n",myCrc.calculate_crc32_k(TestString, 9));
//        System.out.printf("Test CRC32 Q:   %08X\n",myCrc.calculate_crc32_q(TestString, 9));
//        System.out.printf("Test Xfer:      %08X\n\n",myCrc.calculate_crc32_xfer(TestString, 9));
//        System.out.printf("Test GSM:       %010X\n",myCrc.calculate_crc40_gsm(TestString, 9));
//        System.out.printf("Test CRC64:     %016X\n",myCrc.calculate_crc64(TestString, 9));
//        System.out.printf("Test CRC64 1B:  %016X\n",myCrc.calculate_crc64_1b(TestString, 9));
//        System.out.printf("Test WE:        %016X\n",myCrc.calculate_crc64_we(TestString, 9));
//        System.out.printf("Test Jones:     %016X\n",myCrc.calculate_crc64_jones(TestString, 9));
    }
    public static short CRC16(short[] TestString){
    	  AllProtocol myCrc = new AllProtocol();
    	  myCrc.init_crc16_normal_tab(AllProtocol.crc_tab_1021_normal, myCrc.P_CCITT);
    	  return myCrc.calculate_crc16_Ccitt_Xmodem(TestString, TestString.length);
    }
}
