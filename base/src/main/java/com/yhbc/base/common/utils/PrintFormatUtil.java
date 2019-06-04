package com.yhbc.base.common.utils;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;

/**
 * 打印排版
 * Created by kangweibo on 2018/3/22.
 */

public class PrintFormatUtil {

    private static final char SPACE = 32;

    /**
     * 铺满整行字符
     * @param paperWidth
     * @param fontSize
     * @param chars
     * @return
     */
    public static String fullLine(int paperWidth, int fontSize, String chars) {
        StringBuilder builder = new StringBuilder();
        if(80 == paperWidth) {
            if(1 == fontSize) {
                int size = 48;
                while (size > 0) {
                    if(!TextUtils.equals(" ", chars)) {
                        builder.append(chars);
                    }else {
                        builder.append(SPACE);
                    }
                    size --;
                }
            }else {
                int size = 24;
                while (size > 0) {
                    if(!TextUtils.equals(" ", chars)) {
                        builder.append(chars);
                    }else {
                        builder.append(SPACE);
                    }
                    size --;
                }
            }
        }else {
            if(1 == fontSize) {
                int size = 32;
                while (size > 0) {
                    if(!TextUtils.equals(" ", chars)) {
                        builder.append(chars);
                    }else {
                        builder.append(SPACE);
                    }
                    size --;
                }
            }else {
                int size = 16;
                while (size > 0) {
                    if(!TextUtils.equals(" ", chars)) {
                        builder.append(chars);
                    }else {
                        builder.append(SPACE);
                    }
                    size --;
                }
            }
        }
        return builder.toString();
    }


    /**
     * 居中两边补位字符
     * @param paperWidth
     * @param fontSize
     * @param content
     * @param replaceChar
     * @return
     */
    public static String alignCenter4PaperWidth(int paperWidth, int fontSize, String content, String replaceChar) {
        if(80 == paperWidth) {
            if(1 == fontSize) {
                return alignCenter(48, content, replaceChar);
            }else {
                return alignCenter(24, content, replaceChar);
            }
        }else {
            if(1 == fontSize) {
                return alignCenter(32, content, replaceChar);
            }else {
                return alignCenter(16, content, replaceChar);
            }
        }
    }


    private static String alignCenter(int size, String content, String replaceChar) {
        StringBuilder sb = new StringBuilder();
        try {
            int lenght = content.getBytes("gbk").length;
            if(lenght < size) {
                int outside = (size - lenght) / 2;
                StringBuilder buffer = new StringBuilder();
                while (outside > 0) {
                    if(!TextUtils.equals(" ", replaceChar)) {
                        buffer.append(replaceChar);
                    }else {
                        buffer.append(SPACE);
                    }
                    outside--;
                }
                sb.append(buffer);
                sb.append(content);
                sb.append(buffer);
            }else {
                sb.append(content);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            sb.append(content);
        }
        return sb.toString();
    }


    /**
     * 靠右对齐
     * @param paperWidth
     * @param fontSize
     * @param content
     * @return
     */
    public static String alignRight4PaperWidth(int paperWidth, int fontSize, String content) {
        if(80 == paperWidth) {
            if(1 == fontSize) {
                return alignRight(48, content);
            }else {
                return alignRight(24, content);
            }
        }else {
            if(1 == fontSize) {
                return alignRight(32, content);
            }else {
                return alignRight(16, content);
            }
        }
    }

    private static String alignRight(int size, String content) {
        StringBuilder sb = new StringBuilder();
        try {
            int length = content.getBytes("gbk").length;
            if(length < size) {
                int x = size - length;
                while (x > 0) {
                    sb.append(SPACE);
                    x--;
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sb.append(content);
        return sb.toString();
    }


    /**
     * 靠左靠右对齐
     * @param paperWidth
     * @param fontSize
     * @param left
     * @param right
     * @return
     */
    public static String alignLeftAndRight4PaperWidth(int paperWidth, int fontSize, String left, String right) {
        if(80 == paperWidth) {
            if(1 == fontSize) {
                return alignLeftAndRight(48, left, right);
            }else {
                return alignLeftAndRight(24, left, right);
            }
        }else {
            if(1 == fontSize) {
                return alignLeftAndRight(32, left, right);
            }else {
                return alignLeftAndRight(16, left, right);
            }
        }
    }


    private static String alignLeftAndRight(int size, String left, String right) {
        StringBuilder sb = new StringBuilder();
        sb.append(left);
        try {
            int leftLength = left.getBytes("gbk").length;
            int rightLength = right.getBytes("gbk").length + 1;
            int leftLast = leftLength % size;
            if(leftLast == 0) leftLast = size;
            //一行显示不下
            if(leftLast + rightLength > size) {
                if(leftLast % 2 != 0) leftLast++;   //奇数剩余转为偶数，防止英文和中文换行
                int x = size - leftLast + size - rightLength;
                while (x > 0) {
                    sb.append(SPACE);
                    x--;
                }
            }
            //一行可以显示的下
            else {
                if(leftLength + rightLength > size && leftLast % 2 != 0) {
                    leftLast++;
                }
                int x = size - leftLast - rightLength;
                while (x > 0) {
                    sb.append(SPACE);
                    x--;
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sb.append(SPACE);
        sb.append(right);
        return sb.toString();
    }


    /**
     * 左、中、右（只有小字体）
     * @param paperWidth
     * @param left
     * @param middle
     * @param right
     * @return
     */
    public static String alignLeftMiddleAndRight4Paper(int paperWidth, String left, String middle, String right) {
        if(80 == paperWidth) {
            return alignLeftMiddleAndRight(48, left, middle, right);
        }else {
            return alignLeftMiddleAndRight(32, left, middle, right);
        }
    }

    private static String alignLeftMiddleAndRight(int size, String left, String middle, String right) {
        int width = 48 == size ? 9 : 7;
        StringBuilder sb = new StringBuilder();
        try {
            //left
            sb.append(left);
            int rightLength = width * 2 + 2;
            int leftLength = left.getBytes("gbk").length;
            int leftLast = leftLength % size;
            if(leftLast == 0) leftLast = size;
            //一行显示不下
            if(leftLast + rightLength > size) {
                if(leftLast % 2 != 0) leftLast++;   //奇数剩余转为偶数，防止英文和中文换行
                int x = size - leftLast + size - rightLength;
                while (x > 0) {
                    sb.append(SPACE);
                    x--;
                }
            }
            //一行可以显示的下
            else {
                if(leftLength + rightLength > size && leftLast % 2 != 0) {
                    leftLast++;
                }
                int x = size - leftLast - rightLength;
                while (x > 0) {
                    sb.append(SPACE);
                    x--;
                }
            }

            //middle
            sb.append(SPACE);
            int middleLength = middle.getBytes("gbk").length;
            int middleSize = width;
            if(middleLength < middleSize) {
                int x = middleSize - middleLength;
                while (x > 0) {
                    sb.append(SPACE);
                    x--;
                }
            }
            sb.append(middle);

            //right
            sb.append(SPACE);
            int rLength = right.getBytes("gbk").length;
            int rSize = width;
            if(rLength < rSize) {
                int x = rSize - rLength;
                while (x > 0) {
                    sb.append(SPACE);
                    x--;
                }
            }
            sb.append(right);


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            sb.append(SPACE + left + SPACE + right);
        }
        return sb.toString();
    }


    /**
     * 4列
     * @param paperWidth
     * @param left
     * @param middle
     * @param right
     * @param fourth
     * @return
     */
    public static String alignFourth4Paper(int paperWidth, String left, String middle, String right, String fourth) {
        if(80 == paperWidth) {
            return alignFourth(48, left, middle, right, fourth);
        }else {
            return alignFourth(32, left, middle, right, fourth);
        }
    }

    private static String alignFourth(int size, String left, String middle, String right, String fourth) {
        int width = 48 == size ? 6 : 6;
        StringBuilder sb = new StringBuilder();
        try {
            //left
            sb.append(left);
            int rightLength = width * 3 + 3;
            int leftLength = left.getBytes("gbk").length;
            int leftLast = leftLength % size;
            if(leftLast == 0) leftLast = size;
            //一行显示不下
            if(leftLast + rightLength > size) {
                if(leftLast % 2 != 0) leftLast++;   //奇数剩余转为偶数，防止英文和中文换行
                int x = size - leftLast + size - rightLength;
                while (x > 0) {
                    sb.append(SPACE);
                    x--;
                }
            }
            //一行可以显示的下
            else {
                if(leftLength + rightLength > size) {
                    leftLast++;
                }
                int x = size - leftLast - rightLength;
                while (x > 0) {
                    sb.append(SPACE);
                    x--;
                }
            }

            //middle
            sb.append(SPACE);
            int middleLength = middle.getBytes("gbk").length;
            int middleSize = width;
            if(middleLength < middleSize) {
                int x = middleSize - middleLength;
                while (x > 0) {
                    sb.append(SPACE);
                    x--;
                }
            }
            sb.append(middle);

            //right
            sb.append(SPACE);
            int rLength = right.getBytes("gbk").length;
            int rSize = width;
            if(rLength < rSize) {
                int x = rSize - rLength;
                while (x > 0) {
                    sb.append(SPACE);
                    x--;
                }
            }
            sb.append(right);

            //fourth
            sb.append(SPACE);
            int fourthLength = fourth.getBytes("gbk").length;
            int fourthSize = width;
            if(fourthLength < fourthSize) {
                int x = fourthSize - fourthLength;
                while (x > 0) {
                    sb.append(SPACE);
                    x--;
                }
            }
            sb.append(fourth);




        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            sb.append(SPACE + left + SPACE + right + SPACE + fourth);
        }
        return sb.toString();
    }

}