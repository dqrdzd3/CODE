DECLARE
  V_LOWER      NUMBER := 1;
  V_UPPER      NUMBER(7) := 1000; --1048575;
  V_NUM        VARCHAR(8);
  V_RANDOM_NUM VARCHAR(3);
  V_1          VARCHAR(4);
  V_2          VARCHAR(4);
  V_3          VARCHAR(4);
  V_4          VARCHAR(4);
  V_5          VARCHAR(4);
  V_6          VARCHAR(4);
  V_7          VARCHAR(4);
  V_8          VARCHAR(4) := 1; --高位，1燃气，5空气质量
  V_RES        VARCHAR(20);
BEGIN

  FOR I IN V_LOWER .. V_UPPER LOOP
    V_NUM := TO_CHAR(I, 'XXXXX');--顺序生成的16进制数字
    -- dbms_output.put_line('i is:'|| v_num);
    V_RANDOM_NUM := ROUND(DBMS_RANDOM.VALUE * 255);--0到255的随机数
    V_RANDOM_NUM := TO_CHAR(V_RANDOM_NUM, 'XX');--转换成十六进制的随机数
    V_RANDOM_NUM := REPLACE(V_RANDOM_NUM, ' ', '0');--转换空格到0
    -- DBMS_OUTPUT.PUT_LINE('random is:' || V_RANDOM_NUM);
  
    V_NUM := REPLACE(V_NUM, ' ', '0');--转换空格到0
    V_NUM := SUBSTR(V_NUM, 2, 6);--顺序生成的十六进制数
  
    -- dbms_output.put_line('i is:'|| v_num);
  
    V_1 := SUBSTR(V_NUM, 5, 1);
    V_2 := SUBSTR(V_NUM, 4, 1);
    V_3 := SUBSTR(V_NUM, 3, 1);
    V_4 := SUBSTR(V_NUM, 2, 1);
    V_5 := SUBSTR(V_NUM, 1, 1);
  
    V_7 := SUBSTR(V_RANDOM_NUM, 2, 1);
    V_6 := SUBSTR(V_RANDOM_NUM, 3, 1);
  
    -- dbms_output.put_line('v_8 is: '|| v_8);
    -- dbms_output.put_line('v_7 is: '|| v_7);
    -- dbms_output.put_line('v_6 is: '|| v_6);
    -- dbms_output.put_line('v_5 is: '|| v_5);
    -- dbms_output.put_line('v_4 is: '|| v_4);
    -- dbms_output.put_line('v_3 is: '|| v_3);
    -- dbms_output.put_line('v_2 is: '|| v_2);
    -- dbms_output.put_line('v_1 is: '|| v_1);
  
    V_RES := V_8 || V_5 || V_1 || V_4 || V_6 || V_3 || V_7 || V_2;
    DBMS_OUTPUT.PUT_LINE(V_RES);
    -- DBMS_OUTPUT.PUT_LINE('');
  END LOOP;

END;

-- select round( dbms_random.value*255) from dual ;
