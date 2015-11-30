/**
 * Copyright (c) 2011 Muh Hon Cheng
 * Created by honcheng on 28/4/11.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining 
 * a copy of this software and associated documentation files (the 
 * "Software"), to deal in the Software without restriction, including 
 * without limitation the rights to use, copy, modify, merge, publish, 
 * distribute, sublicense, and/or sell copies of the Software, and to 
 * permit persons to whom the Software is furnished to do so, subject 
 * to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be 
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT 
 * WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT 
 * SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE 
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, 
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR 
 * IN CONNECTION WITH THE SOFTWARE OR 
 * THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * @author 		Muh Hon Cheng <honcheng@gmail.com>
 * @copyright	2011	Muh Hon Cheng
 * @version
 * 
 */

#import "PCLineChartView.h"

@implementation PCLineChartViewComponent
@synthesize title, points, colour, shouldLabelValues, labelFormat;
- (id)init
{
    self = [super init];
    if (self)
    {
        self.labelFormat = @"%.1f%%";
    }
    return self;
}

@end

@implementation PCLineChartView
@synthesize components;
@synthesize interval, minValue, maxValue;
@synthesize xLabels;
@synthesize yLabelFont, xLabelFont, valueLabelFont, legendFont;
@synthesize autoscaleYAxis, numYIntervals;
@synthesize numXIntervals;

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        // Initialization code
        
        [self setBackgroundColor:[UIColor clearColor]];
        interval = 2;
		maxValue = 10;
		minValue = 0;
        numYIntervals = 5;
        numXIntervals = 1;
		yLabelFont = [UIFont systemFontOfSize:9];
		xLabelFont = [UIFont systemFontOfSize:9];
		valueLabelFont = [UIFont systemFontOfSize:9];
		legendFont = [UIFont systemFontOfSize:9];
        pointbuttons = [[NSMutableArray alloc]init];
		
    }
    return self;
}

-(void)ClickPointButton:(id)sender{
    NSLog(@"ClickPointButton:%d",[sender tag]);
    for (UIButton *btn in pointbuttons) {
        UILabel *view = (UILabel *)[btn viewWithTag:100];
        view.backgroundColor = [UIColor whiteColor];
    }
    UIButton *btnpoint = [pointbuttons objectAtIndex:[sender tag]];
    UILabel *view = (UILabel *)[btnpoint viewWithTag:100];
    view.backgroundColor = [UIColor grayColor];
    
    if (labelpointvalue == nil) {
        labelpointvalue = [[UILabel alloc]initWithFrame:CGRectMake(btnpoint.frame.origin.x-40, btnpoint.frame.origin.y+btnpoint.frame.size.height+20, 80, 30)];
        labelpointvalue.backgroundColor = [UIColor clearColor];
        labelpointvalue.font = [UIFont systemFontOfSize:9.0];
        labelpointvalue.textColor = [UIColor whiteColor];
        labelpointvalue.textAlignment = NSTextAlignmentCenter;
        labelpointvalue.numberOfLines = 2;
    }
    [self addSubview:labelpointvalue];
    PCLineChartViewComponent *tempcomponent = [components objectAtIndex:0];
    NSString *strValue = [tempcomponent.points objectAtIndex:[sender tag]];
    
    //NSLog(@"%f-%f",btnpoint.frame.origin.x,self.frame.size.width);
    if (btnpoint.frame.origin.y > (self.frame.size.height-50)) {
        labelpointvalue.frame = CGRectMake(btnpoint.frame.origin.x-(70-btnpoint.frame.size.width)/2, btnpoint.frame.origin.y-10, 70, 20);
    }
    else{
        labelpointvalue.frame = CGRectMake(btnpoint.frame.origin.x-(70-btnpoint.frame.size.width)/2, btnpoint.frame.origin.y+btnpoint.frame.size.height-10, 70, 20);
    }
    labelpointvalue.text = [NSString stringWithFormat:@"(%@)",strValue];
    
}
- (void)drawRect:(CGRect)rect
{
    if (pointbuttons.count != 0) {
        [pointbuttons removeAllObjects];
    }
    CGContextRef ctx = UIGraphicsGetCurrentContext();
    UIGraphicsPushContext(ctx);
    CGContextSetRGBFillColor(ctx, 1.0f, 1.0f, 1.0f, 1.0f);
    
    int n_div = 0;
    int power = 0;
    float scale_min, scale_max, div_height;
    float top_margin = 10;
    float bottom_margin = 10;
	float x_label_height = 20;
	
    if (autoscaleYAxis) {
        scale_min = 0.0;
        power = floor(log10(maxValue/5)); 
        float increment = maxValue / (5 * pow(10,power));
        increment = (increment <= 5) ? ceil(increment) : 10;
        increment = increment * pow(10,power);
        scale_max = 5 * increment;
        self.interval = scale_max / numYIntervals;
    }
    else {
        scale_min = minValue;
        scale_max = maxValue;
    }
    n_div = (scale_max-scale_min)/self.interval + 1;
    div_height = (self.frame.size.height-top_margin-bottom_margin-x_label_height)/(n_div-1);
    
    for (int i = 0; i < n_div; i++)
    {
        float y_axis = scale_max - i*self.interval;
        int y = top_margin + div_height*i;
        CGRect textFrame = CGRectMake(0,y-8,25,20);
        NSString *formatString = [NSString stringWithFormat:@"%%.%if", (power < 0) ? -power : 0];
        NSString *text = [NSString stringWithFormat:formatString, y_axis];
        
        NSMutableParagraphStyle *paragraph = [[NSMutableParagraphStyle alloc] init];
        paragraph.alignment = NSTextAlignmentRight;
        paragraph.lineBreakMode = NSLineBreakByWordWrapping;
        NSDictionary *dicAttribute = [NSDictionary dictionaryWithObjectsAndKeys:yLabelFont,NSFontAttributeName,paragraph, NSParagraphStyleAttributeName,[UIColor whiteColor],NSForegroundColorAttributeName,nil];
        [text drawInRect:textFrame withAttributes:dicAttribute];
		
		// These are "grid" lines
//        CGContextSetLineWidth(ctx, 1);
//        CGContextSetRGBStrokeColor(ctx, 0.4f, 0.4f, 0.4f, 0.1f);
//        CGContextMoveToPoint(ctx, 30, y);
//        CGContextAddLineToPoint(ctx, self.frame.size.width-30, y);
//        CGContextStrokePath(ctx);
    }
    
    float margin = 50;
    float div_width = (self.frame.size.width-2*margin)/([self.xLabels count]-1);
    for (NSUInteger i = 0; i < [self.xLabels count]; i++)
    {
        if (i % numXIntervals == 1 || numXIntervals == 1) {
            int x = (int) (margin + div_width * i);
            NSString *x_label = [NSString stringWithFormat:@"%@", [self.xLabels objectAtIndex:i]];
            CGRect textFrame = CGRectMake(x - 100, self.frame.size.height - x_label_height, 200, x_label_height);
            
            
            NSMutableParagraphStyle *paragraph = [[NSMutableParagraphStyle alloc] init];
            paragraph.alignment = NSTextAlignmentCenter;
            paragraph.lineBreakMode = NSLineBreakByWordWrapping;
            NSDictionary *dicAttribute = [NSDictionary dictionaryWithObjectsAndKeys:xLabelFont,NSFontAttributeName,paragraph, NSParagraphStyleAttributeName,[UIColor whiteColor],NSForegroundColorAttributeName,nil];
            [x_label drawInRect:textFrame withAttributes:dicAttribute];
        };
    }
	//CGColorRef shadowColor = [[UIColor lightGrayColor] CGColor];
    //CGContextSetShadowWithColor(ctx, CGSizeMake(0,-1), 1, shadowColor);
	
    NSMutableArray *legends = [NSMutableArray array];
    float circle_diameter = 10;
    float circle_stroke_width = 1;
    float line_width = 1;
	
    for (PCLineChartViewComponent *component in self.components)
    {
        int last_x = 0;
        int last_y = 0;
        
        if (!component.colour)
        {
            component.colour = PCColorBlue;
        }
		for (int x_axis_index = 0; x_axis_index < [component.points count]; x_axis_index++)
        {
            id object = [component.points objectAtIndex:x_axis_index];
            if (object != [NSNull null] && object)
            {
                float value = [object floatValue];
                BOOL bofflineCharLine = NO;
                if (value == [OffLineChartLineCode floatValue]) {
                    bofflineCharLine = YES;
                }
				//CGContextSetStrokeColorWithColor(ctx, [component.colour CGColor]);
                //CGContextSetLineWidth(ctx, circle_stroke_width);
        
                int x = margin + div_width*x_axis_index;
                int y = top_margin + (scale_max-value)/self.interval*div_height;
                
                //wb 除了这一段其他注释都为原代码
                UIButton *btntemp = [[UIButton alloc]initWithFrame: CGRectMake(x-15, y-15, 30,30)];
                [btntemp addTarget:self action:@selector(ClickPointButton:) forControlEvents:UIControlEventTouchUpInside];
                btntemp.backgroundColor = [UIColor clearColor];
                btntemp.layer.masksToBounds = YES;
                btntemp.layer.cornerRadius = 15;
                btntemp.tag = x_axis_index;
                
                
                //此处因为按钮太小点击困难
                UILabel *viewtemp = [[UILabel alloc]initWithFrame:CGRectMake((30-circle_diameter)/2, (30-circle_diameter)/2, circle_diameter, circle_diameter)];
                viewtemp.backgroundColor = [UIColor whiteColor];
                viewtemp.layer.masksToBounds = YES;
                viewtemp.layer.cornerRadius = 5;
                [btntemp addSubview:viewtemp];
                viewtemp.tag = 100;
                [self addSubview:btntemp];
                [pointbuttons addObject:btntemp];
                
                NSLog(@"%d",x-15);
                if (bofflineCharLine) {
                    btntemp.hidden = YES;
                    viewtemp.hidden = YES;
                }
                else{
                    btntemp.hidden = NO;
                    viewtemp.hidden = NO;
                }
                
                ////////////////
				//CGContextSetFillColorWithColor(ctx, [component.colour CGColor]);
                
                if (last_x != 0 && last_y != 0)
                {
                    float distance = sqrt( pow(x-last_x, 2) + pow(y-last_y,2) );
                    if (value != [OffLineChartLineCode floatValue]) {
                        float last_x1 = last_x + (circle_diameter/2) / distance * (x-last_x);
                        float last_y1 = last_y + (circle_diameter/2) / distance * (y-last_y);
                        float x1 = x - (circle_diameter/2) / distance * (x-last_x);
                        float y1 = y - (circle_diameter/2) / distance * (y-last_y);
                        
                        CGContextSetLineWidth(ctx, line_width);
                        CGContextMoveToPoint(ctx, last_x1, last_y1);
                        CGContextAddLineToPoint(ctx, x1, y1);
                        CGContextStrokePath(ctx);
                    }
                }
                
                if (x_axis_index == [component.points count]-1)
                {
                    NSMutableDictionary *info = [NSMutableDictionary dictionary];
                    if (component.title)
                    {
                        [info setObject:component.title forKey:@"title"];
                    }
                    [info setObject:[NSNumber numberWithFloat:x+circle_diameter/2+15] forKey:@"x"];
                    [info setObject:[NSNumber numberWithFloat:y-10] forKey:@"y"];
					[info setObject:component.colour forKey:@"colour"];
                    [legends addObject:info];
				}
                
                if (bofflineCharLine) {
                    last_x = 0;
                    last_y = 0;
                }
                else {
                    last_x = x;
                    last_y = y;
                }

            }
            
        }
    }
//    for (int i=0; i<[self.xLabels count]; i++)
//    {
//        int y_level = top_margin;
//		
//        for (int j=0; j<[components count]; j++)
//        {
//			NSArray *items = [[components objectAtIndex:j] points];
//            id object = [items objectAtIndex:i];
//            if (object!=[NSNull null] && object)
//            {
//                float value = [object floatValue];
//                int x = margin + div_width*i;
//                int y = top_margin + (scale_max-value)/self.interval*div_height;
//                int y1 = y - circle_diameter/2 - valueLabelFont.pointSize;
//                int y2 = y + circle_diameter/2;
//                
//				if ([[components objectAtIndex:j] shouldLabelValues]) {
//					if (y1 > y_level)
//					{
//						CGContextSetRGBFillColor(ctx, 0.0f, 0.0f, 0.0f, 1.0f);
//						NSString *perc_label = [NSString stringWithFormat:[[components objectAtIndex:j] labelFormat], value];
//						CGRect textFrame = CGRectMake(x-25,y1, 50,20);
//						[perc_label drawInRect:textFrame 
//									  withFont:valueLabelFont 
//								 lineBreakMode:NSLineBreakByWordWrapping
//									 alignment:NSTextAlignmentCenter];
//						y_level = y1 + 20;
//					}
//					else if (y2 < y_level+20 && y2 < self.frame.size.height-top_margin-bottom_margin)
//					{
//						CGContextSetRGBFillColor(ctx, 0.0f, 0.0f, 0.0f, 1.0f);
//						NSString *perc_label = [NSString stringWithFormat:[[components objectAtIndex:j] labelFormat], value];
//						CGRect textFrame = CGRectMake(x-25,y2, 50,20);
//						[perc_label drawInRect:textFrame 
//									  withFont:valueLabelFont 
//								 lineBreakMode:NSLineBreakByWordWrapping
//									 alignment:NSTextAlignmentCenter];
//						y_level = y2 + 20;
//					}
//					else
//					{
//						CGContextSetRGBFillColor(ctx, 0.0f, 0.0f, 0.0f, 1.0f);
//						NSString *perc_label = [NSString stringWithFormat:[[components objectAtIndex:j] labelFormat], value];
//						CGRect textFrame = CGRectMake(x-50,y-10, 50,20);
//						[perc_label drawInRect:textFrame 
//									  withFont:valueLabelFont 
//								 lineBreakMode:NSLineBreakByWordWrapping
//									 alignment:NSTextAlignmentCenter];
//						y_level = y1 + 20;
//					}
//                }
//                if (y+circle_diameter/2>y_level) y_level = y+circle_diameter/2;
//            }
//            
//        }
//    }
//    
//	NSSortDescriptor *sortDesc = [NSSortDescriptor sortDescriptorWithKey:@"y" ascending:YES];
//	[legends sortUsingDescriptors:[NSArray arrayWithObject:sortDesc]];
//	
//    //CGContextSetRGBFillColor(ctx, 0.0f, 0.0f, 0.0f, 1.0f);
//    float y_level = 0;
//    for (NSMutableDictionary *legend in legends)
//    {
//		UIColor *colour = [legend objectForKey:@"colour"];
//		CGContextSetFillColorWithColor(ctx, [colour CGColor]);
//		
//        NSString *title = [legend objectForKey:@"title"];
//        float x = [[legend objectForKey:@"x"] floatValue];
//        float y = [[legend objectForKey:@"y"] floatValue];
//        if (y<y_level)
//        {
//            y = y_level;
//        }
//        
//        CGRect textFrame = CGRectMake(x,y,margin,15);
//        [title drawInRect:textFrame withFont:legendFont];
//        
//        y_level = y + 15;
//    }
}
@end
