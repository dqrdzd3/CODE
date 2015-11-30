//
//  LineView.m
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import "LineView.h"

#define BottomGap 20
#define ColWidth 20

#define Color_MiddleLightGray [UIColor colorWithRed:205.0/255.0 green:205.0/255.0 blue:205.0/255.0 alpha:1.0]
#define Color_MiddleGray [UIColor colorWithRed:107.0/255.0 green:121.0/255.0 blue:136.0/255.0 alpha:1.0]
#define Color_Gray [UIColor colorWithRed:51.0/255.0 green:51.0/255.0 blue:51.0/255.0 alpha:1.0]
@implementation LineView
@synthesize delegate;

-(id)initWithFrame:(CGRect)frame{
    self = [super initWithFrame:frame];
    if (self) {
        rect = frame;
        self.backgroundColor = [UIColor clearColor];
    }
    return self;
}

-(void)ReloadData:(NSInteger)XMax Ymax:(float)Ymax
           Xcount:(NSInteger)Xcount Ycount:(NSInteger)Ycount
        lineColor:(UIColor *)Color data:(NSArray *)data{
    iXmaxValue = XMax;
    iXcount = Xcount;
    
    fYmaxValue = Ymax;
    iYcount = Ycount;
    
    cLineColor = Color;
    if (maryData.count != 0) {
        [maryData removeAllObjects];
    }
    if (maryData == nil) {
        maryData = [[NSMutableArray alloc]init];
    }
    [maryData addObjectsFromArray:data];
    
    fLineDrawViewHeight = rect.size.height-BottomGap;
    fRowHeight = fLineDrawViewHeight/iYcount;
    
    [self setNeedsDisplay];
}


-(CGPoint)GetPoint:(float)value xoffset:(float)xoffset{
    float fy = (fRowHeight+(fYmaxValue-value)*(fLineDrawViewHeight-fRowHeight)/(float)fYmaxValue);
    CGPoint p = CGPointMake(xoffset+1,[[NSString stringWithFormat:@"%.2f",fy] floatValue]);
    return p;
}


- (void)drawPoint:(CGContextRef)context point:(CGPoint)point color:(UIColor *)color{
    CGContextSetShouldAntialias(context, YES ); //抗锯齿
    CGColorSpaceRef Pointcolorspace1 = CGColorSpaceCreateDeviceRGB();
    CGContextSetStrokeColorSpace(context, Pointcolorspace1);
    CGContextSetStrokeColorWithColor(context, color.CGColor);
    if (isnan(point.x)) {
        NSLog(@"point.x nan!");
    }
    if (isnan(point.y)) {
        NSLog(@"point.y nan!");
    }
    CGContextMoveToPoint(context, point.x,point.y);
    CGContextAddArc(context, point.x, point.y, 2, 0, 360, 0);
    CGContextClosePath(context);
    CGContextSetFillColorWithColor(context, color.CGColor);
    CGContextFillPath(context);
    CGColorSpaceRelease(Pointcolorspace1);
}
- (void)drawLine:(CGContextRef)context startPoint:(CGPoint)startPoint endPoint:(CGPoint)endPoint lineColor:(UIColor *)lineColor{
    
    CGContextSetShouldAntialias(context, YES ); //抗锯齿
    CGColorSpaceRef Linecolorspace1 = CGColorSpaceCreateDeviceRGB();
    CGContextSetStrokeColorSpace(context, Linecolorspace1);
    CGContextSetLineWidth(context, 0.5);
    CGContextSetStrokeColorWithColor(context, lineColor.CGColor);
    CGContextMoveToPoint(context, startPoint.x, startPoint.y);
    CGContextAddLineToPoint(context, endPoint.x, endPoint.y);
    CGContextStrokePath(context);
    CGColorSpaceRelease(Linecolorspace1);
}


- (void)drawText:(CGContextRef)context text:(NSString*)text point:(CGPoint)point color:(UIColor *)color font:(UIFont*)font textAlignment:(NSTextAlignment)textAlignment
{
    //    UIFont *font = [UIFont systemFontOfSize: fontSize];
    [color set];
    NSDictionary *attributes = @{NSFontAttributeName:font};
    CGSize title1Size = [text sizeWithAttributes:attributes];
    CGRect titleRect1 = CGRectMake(point.x,
                                   point.y,
                                   title1Size.width,
                                   title1Size.height);
    [text drawInRect:titleRect1 withFont:font lineBreakMode:NSLineBreakByClipping alignment:textAlignment];
}

// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
    CGContextRef context = UIGraphicsGetCurrentContext();
    for (NSInteger i = 0; i < iYcount; i++) {
        CGPoint startPoint = CGPointMake(0, (i+1)*fLineDrawViewHeight/iYcount);
        CGPoint endPoint = CGPointMake(rect.size.width, (i+1)*fLineDrawViewHeight/iYcount);
        [self drawLine:context startPoint:startPoint endPoint:endPoint lineColor:Color_MiddleLightGray];
    }
    
    float fxOffset = 0;
    for (NSInteger i = 0; i < iXcount; i++) {
        fxOffset = (i+1)*ColWidth;
        CGPoint startPoint = CGPointMake(fxOffset, 0);
        CGPoint endPoint = CGPointMake(fxOffset, fLineDrawViewHeight);
        [self drawLine:context startPoint:startPoint endPoint:endPoint lineColor:Color_MiddleLightGray];
        
        if (i == iXcount-1) {
            [delegate LineViewDrawPoint:endPoint colWidth:ColWidth];
        }
        if (i > 0) {
            int textgap = 0;
            if (i < 10) {
                textgap = 2;
            }
            else{
                textgap = 5;
            }
            CGPoint Point = CGPointMake(i*ColWidth-textgap, fLineDrawViewHeight+3);
            NSString *strText = [NSString stringWithFormat:@"%d",i];
            [self drawText:context text:strText point:Point color:Color_Gray font:[UIFont systemFontOfSize:9.0] textAlignment:NSTextAlignmentCenter];
        }
    }
    
    if (maryData.count > 0) {
        float fPointxOffset = 0.0;
        NSString *strValueold = [maryData objectAtIndex:0];
        CGPoint oldPoint = [self GetPoint:[strValueold floatValue] xoffset:fPointxOffset];
        
        for (NSInteger i = 0;i < maryData.count;i++) {
            if (i == maryData.count-1) {
                [self drawPoint:context point:oldPoint color:cLineColor];
                continue;
            }
            [self drawPoint:context point:oldPoint color:cLineColor];
            fPointxOffset = fPointxOffset+ColWidth;
            NSString *strValuenew = [maryData objectAtIndex:i+1];
            CGPoint newPoint = [self GetPoint:[strValuenew floatValue] xoffset:fPointxOffset];
            [self drawLine:context startPoint:oldPoint endPoint:newPoint lineColor:cLineColor];
            oldPoint = newPoint;
        }
    }

}

@end
