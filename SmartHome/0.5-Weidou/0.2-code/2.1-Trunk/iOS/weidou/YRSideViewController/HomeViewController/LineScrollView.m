//
//  LineScrollView.m
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import "LineScrollView.h"


#define BottomGap 20

#define Color_MiddleLightGray [UIColor colorWithRed:205.0/255.0 green:205.0/255.0 blue:205.0/255.0 alpha:1.0]
#define Color_MiddleGray [UIColor colorWithRed:107.0/255.0 green:121.0/255.0 blue:136.0/255.0 alpha:1.0]
#define Color_Gray [UIColor colorWithRed:51.0/255.0 green:51.0/255.0 blue:51.0/255.0 alpha:1.0]

@implementation LineScrollView
@synthesize dataSource;

-(id)initWithFrame:(CGRect)frame{
    self = [super initWithFrame:frame];
    if (self) {
        self.backgroundColor = [UIColor clearColor];
        rect = frame;
        fLeftGap = 25;
        leftFont = [UIFont systemFontOfSize:9.0];
        iXmaxValue = 1;
        iXcount = 1;
        
        fYmaxValue = 2.0;
        iYcount = 5;
        cLineColor = [UIColor redColor];
        viewColor.backgroundColor = cLineColor;
        
        [self LoadView];
    }
    return self;
}

-(void)ReloadData{
    iXmaxValue = [dataSource LineScrollViewOfXmaxValue:self];
    iXcount = [dataSource LineScrollViewOfXCount:self];
    if (iXcount == 1) {
        scrollView.contentSize = CGSizeMake(rect.size.width, rect.size.height);
        [scrollView setContentOffset:CGPointMake(0, 0) animated:NO];
        CGRect frame = lineview.frame;
        frame.size.width = scrollView.contentSize.width;
        lineview.frame = frame;
    }
    fYmaxValue = [dataSource LineScrollViewOfYmaxValue:self];
    iYcount = [dataSource LineScrollViewOfYCount:self];
    cLineColor = [dataSource LineScrollViewOfLineColor:self];
    labelWhitch.text = [dataSource LineScrollViewOfLineName:self];
    viewColor.backgroundColor = cLineColor;
    itype = [dataSource LineScrollViewOfType:self];

    NSString *strText = @"";
    if (itype == 1) {
        strText = [NSString stringWithFormat:@"%.2f",fYmaxValue];
    }
    else if (itype == 2){
        strText = [NSString stringWithFormat:@"%.3f",fYmaxValue];
    }
    else if (itype == 3){
        strText = [NSString stringWithFormat:@"%.1f",fYmaxValue];
    }
    NSDictionary *attributes = @{NSFontAttributeName:leftFont};
    CGSize title1Size = [strText sizeWithAttributes:attributes];
    //fLeftGap = title1Size.width+5;

    //scrollView.frame = CGRectMake(fLeftGap, 0, rect.size.width-fLeftGap,rect.size.height);
    //viewColor.frame = CGRectMake(fLeftGap, rect.size.height-BottomGap, 10, 10);
    [self setNeedsDisplay];
    
    [lineview ReloadData:iXmaxValue Ymax:fYmaxValue Xcount:iXcount Ycount:iYcount lineColor:cLineColor data:[dataSource LineScrollViewOfData:self]];
}

-(void)LoadView{
    scrollView = [[UIScrollView alloc]initWithFrame:CGRectMake(fLeftGap, 0, rect.size.width-fLeftGap, rect.size.height)];
    scrollView.contentSize = CGSizeMake(rect.size.width, rect.size.height);
    scrollView.backgroundColor = [UIColor clearColor];
    [scrollView setBounces:NO];
    scrollView.showsHorizontalScrollIndicator = NO;
    scrollView.showsVerticalScrollIndicator = NO;
    [self addSubview:scrollView];
    
    lineview = [[LineView alloc]initWithFrame:CGRectMake(0, 0, scrollView.contentSize.width, scrollView.frame.size.height-BottomGap)];
    lineview.delegate = self;
    [scrollView addSubview:lineview];
    
    viewColor = [[UIView alloc]initWithFrame:CGRectMake(fLeftGap, rect.size.height-BottomGap, 10, 10)];
    viewColor.backgroundColor = [UIColor whiteColor];
    [self addSubview:viewColor];
    
    labelWhitch = [[UILabel alloc]initWithFrame:CGRectMake(viewColor.frame.origin.x+viewColor.frame.size.width+5, viewColor.frame.origin.y, rect.size.width-viewColor.frame.origin.x-viewColor.frame.size.width-5, 10)];
    labelWhitch.backgroundColor = [UIColor clearColor];
    labelWhitch.font = [UIFont systemFontOfSize:9.0];
    labelWhitch.textColor = Color_Gray;
    labelWhitch.text = @"";
    [self addSubview:labelWhitch];
    
    //[self ReloadData];
}
- (void)drawPoint:(CGContextRef)context point:(CGPoint)point color:(UIColor *)color{
    
    CGContextSetShouldAntialias(context, YES ); //抗锯齿
    CGColorSpaceRef Pointcolorspace1 = CGColorSpaceCreateDeviceRGB();
    CGContextSetStrokeColorSpace(context, Pointcolorspace1);
    CGContextSetStrokeColorWithColor(context, color.CGColor);
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
    [color set];
    NSDictionary *attributes = @{NSFontAttributeName:font};
    CGSize title1Size = [text sizeWithAttributes:attributes];
    CGRect titleRect1 = CGRectMake(point.x,
                                   point.y,
                                   fLeftGap-3,
                                   title1Size.height);
    [text drawInRect:titleRect1 withFont:font lineBreakMode:NSLineBreakByCharWrapping alignment:textAlignment];
}
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
    CGContextRef context = UIGraphicsGetCurrentContext();
    
    CGPoint startPoint = CGPointMake(fLeftGap, 0);
    CGPoint endPoint = CGPointMake(fLeftGap, rect.size.height-2*BottomGap);
    [self drawLine:context startPoint:startPoint endPoint:endPoint lineColor:Color_MiddleGray];
    
    for (NSInteger i = 0; i < iYcount; i++) {
        CGPoint Point = CGPointMake(0, (i+1)*(scrollView.frame.size.height-2*BottomGap)/iYcount-5);
        
        NSString *strText = @"";
        if (itype == 1) {
            strText = [NSString stringWithFormat:@"%.2f",(iYcount-1-i)*fYmaxValue/(iYcount-1)];
        }
        else if (itype == 2){
            strText = [NSString stringWithFormat:@"%.3f",(iYcount-1-i)*fYmaxValue/(iYcount-1)];
        }
        else if (itype == 3){
            strText = [NSString stringWithFormat:@"%.1f",(iYcount-1-i)*fYmaxValue/(iYcount-1)];
        }
        if (i == (iYcount-1)) {
            strText = @"0";
        }
        [self drawText:context text:strText point:Point color:Color_Gray font:leftFont textAlignment:NSTextAlignmentRight];
    }
}

-(void)LineViewDrawPoint:(CGPoint)point colWidth:(float)colwidth{
    if (point.x+fLeftGap >= rect.size.width) {
        CGSize size = scrollView.contentSize;
        size.width = point.x+colwidth;
        scrollView.contentSize = size;
        
        CGRect frame = lineview.frame;
        frame.size.width = size.width;
        lineview.frame = frame;
        [scrollView setContentOffset:CGPointMake(scrollView.contentSize.width-scrollView.frame.size.width, 0) animated:NO];
    }
}

@end
