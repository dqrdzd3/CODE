//
//  ComeOnView.m
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import "ComeOnView.h"

#define match_up_1 [UIColor colorWithRed:251.0/255 green:151.0/255 blue:115.0/255 alpha:1.0]
#define match_up_2 [UIColor colorWithRed:130.0/255 green:188.0/255 blue:232.0/255 alpha:1.0]
#define match_up_3 [UIColor colorWithRed:255.0/255 green:156.0/255 blue:217.0/255 alpha:1.0]

#define match_middle_1 [UIColor colorWithRed:221.0/255 green:156.0/255 blue:255.0/255 alpha:1.0]
#define match_middle_2 [UIColor colorWithRed:212.0/255 green:255.0/255 blue:156.0/255 alpha:1.0]
#define match_middle_3 [UIColor colorWithRed:255.0/255 green:210.0/255 blue:132.0/255 alpha:1.0]

#define match_down_1 [UIColor colorWithRed:121.0/255 green:226.0/255 blue:175.0/255 alpha:1.0]
#define match_down_2 [UIColor colorWithRed:156.0/255 green:165.0/255 blue:255.0/255 alpha:1.0]
#define match_down_3 [UIColor colorWithRed:102.0/255 green:216.0/255 blue:239.0/255 alpha:1.0]
@implementation ComeOnView
@synthesize labelCurValue;
@synthesize labelUnit;
/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/
-(id)initWithFrame:(CGRect)frame{
    self = [super initWithFrame:frame];
    if (self) {
        rect = frame;
        maryValue = [[NSMutableArray alloc]init];
        maryRankValue = [[NSMutableArray alloc]init];
        maryButton = [[NSMutableArray alloc]init];
        [self LoadSubView];
    }
    return self;
}
-(void)ReloadData{
    labelCurValue.text = [RealTimeModel SharedRealTimeModel].strPieValue;
    NSDictionary *attributes = @{NSFontAttributeName:labelCurValue.font};
    CGSize size = [labelCurValue.text sizeWithAttributes:attributes];
    CGRect frame = labelCurValue.frame;
    frame.size.width = size.width+5;
    labelCurValue.frame = frame;
    labelUnit.frame = CGRectMake(labelCurValue.frame.origin.x+labelCurValue.frame.size.width, 20, 50, 20);
    labelUnit.text = [RealTimeModel SharedRealTimeModel].strUnit;
}
-(void)LoadSubView{
    UILabel *labelLeft = [[UILabel alloc]initWithFrame:CGRectMake(10, 20, 50, 20)];
    labelLeft.backgroundColor = [UIColor clearColor];
    labelLeft.font = [UIFont systemFontOfSize:14.0];
    labelLeft.textColor = Color_MiddleHei;
    labelLeft.text = @"当前值";
    [self addSubview:labelLeft];
    
    labelCurValue = [[UILabel alloc]initWithFrame:CGRectMake(labelLeft.frame.origin.x+labelLeft.frame.size.width, 0, rect.size.width-50, 40)];
    labelCurValue.backgroundColor = [UIColor clearColor];
    labelCurValue.font = [UIFont boldSystemFontOfSize:35.0];
    labelCurValue.textColor = Color_MiddleHei;
    labelCurValue.text = [RealTimeModel SharedRealTimeModel].strPieValue;
    [self addSubview:labelCurValue];
    
    NSDictionary *attributes = @{NSFontAttributeName:labelCurValue.font};
    CGSize size = [labelCurValue.text sizeWithAttributes:attributes];
    CGRect frame = labelCurValue.frame;
    frame.size.width = size.width+5;
    labelCurValue.frame = frame;
    
    
    labelUnit = [[UILabel alloc]initWithFrame:CGRectMake(labelCurValue.frame.origin.x+labelCurValue.frame.size.width, 20, 50, 20)];
    labelUnit.backgroundColor = [UIColor clearColor];
    labelUnit.font = [UIFont systemFontOfSize:14.0];
    labelUnit.textColor = Color_MiddleHei;
    labelUnit.text = [RealTimeModel SharedRealTimeModel].strUnit;
    [self addSubview:labelUnit];
    
    btnRefresh = [[UIButton alloc]initWithFrame:CGRectMake(SCREENWIDTH-labelCurValue.frame.size.height-10, labelCurValue.frame.origin.y, labelCurValue.frame.size.height, labelCurValue.frame.size.height)];
    //[btnRefresh setImage:[UIImage imageNamed:@"Icon.png"] forState:UIControlStateNormal];
    btnRefresh.backgroundColor = [UIColor colorWithRed:76/255.0 green:179/255.0 blue:214/255.0 alpha:1.0];
    [btnRefresh setTitle:@"刷新" forState:UIControlStateNormal];
    btnRefresh.titleLabel.font = [UIFont systemFontOfSize:14.0];
    [btnRefresh setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    btnRefresh.layer.cornerRadius = 2.0;
    [btnRefresh addTarget:self action:@selector(RefreshRank) forControlEvents:UIControlEventTouchUpInside];
    [self addSubview:btnRefresh];
    
    float FightButtonHeight = (FULLSCREENHEIGHT-TopBarHeight-BottomViewHeight-labelCurValue.frame.origin.y-labelCurValue.frame.size.height-10)/3;
    for (NSInteger i = 0; i < 3; i++) {
        for (NSInteger j = 0; j < 3; j++) {
            FightButton *btn = [[FightButton alloc]initWithFrame:CGRectMake((j%3)*SCREENWIDTH/3.0, labelCurValue.frame.origin.y+labelCurValue.frame.size.height+10+FightButtonHeight*i, SCREENWIDTH/3.0, FightButtonHeight)];
            btn.tag = i*3+j;
            switch (btn.tag) {
                case 0:
                    btn.backgroundColor = match_up_1;
                    break;
                case 1:
                    btn.backgroundColor = match_up_2;
                    break;
                case 2:
                    btn.backgroundColor = match_up_3;
                    break;
                case 3:
                    btn.backgroundColor = match_middle_1;
                    break;
                case 4:
                    btn.backgroundColor = match_middle_2;
                    break;
                case 5:
                    btn.backgroundColor = match_middle_3;
                    break;
                case 6:
                    btn.backgroundColor = match_down_1;
                    break;
                case 7:
                    btn.backgroundColor = match_down_2;
                    break;
                case 8:
                    btn.backgroundColor = match_down_3;
                    break;
                default:
                    break;
            }
            btn.titleLabel.font = [UIFont systemFontOfSize:14.0];
            [btn setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
            [self addSubview:btn];
            [btn addTarget:self action:@selector(ClickColor:) forControlEvents:UIControlEventTouchUpInside];
        }
    }
}

-(void)RefreshRank{
    for (FightButton *btn in maryButton) {
        btn.labelRank.text = @"";
        btn.labelStatus.text = @"";
        btn.labelValue.text = @"";
    }
    if (maryButton.count != 0) {
        [maryButton removeAllObjects];
    }
}
-(void)ClickColor:(id)sender{
    FightButton *btnsender = (FightButton *)sender;
    if ([maryButton indexOfObject:btnsender] == NSNotFound) {
        [maryButton addObject:btnsender];
    }
    else{
        [maryButton replaceObjectAtIndex:[maryButton indexOfObject:btnsender] withObject:btnsender];
    }
    btnsender.labelValue.text = [RealTimeModel SharedRealTimeModel].strPieValue;
    btnsender.labelStatus.text = [RealTimeModel SharedRealTimeModel].strStatus;
    
    NSComparator cmptr = ^(FightButton *obj1, FightButton* obj2){
        if ([obj1.labelValue.text floatValue] > [obj2.labelValue.text floatValue]) {
            return (NSComparisonResult)NSOrderedDescending;
        }
        if ([obj1.labelValue.text floatValue] < [obj2.labelValue.text floatValue]) {
            return (NSComparisonResult)NSOrderedAscending;
        }
        return (NSComparisonResult)NSOrderedSame;
    };
    NSArray *array = [maryButton sortedArrayUsingComparator:cmptr];
    FightButton *lastbtn = nil;
    NSInteger irank = 1;
    for (NSInteger i = array.count-1; i >= 0; i--) {
        FightButton *btn = [array objectAtIndex:i];
        if (lastbtn != nil && ([btn.labelValue.text floatValue] == [lastbtn.labelValue.text floatValue])) {
            btn.labelRank.text = lastbtn.labelRank.text;
        }
        else{
            btn.labelRank.text = [NSString stringWithFormat:@"第%d名",irank];
            irank++;
        }
        lastbtn = btn;
    }
    [RealTimeModel SharedRealTimeModel].strFightShare = @"";
    [RealTimeModel SharedRealTimeModel].strFightShare = [NSString stringWithFormat:@"比赛(%@):%@,%@,%@",[RealTimeModel SharedRealTimeModel].strName,btnsender.labelRank.text,btnsender.labelValue.text,btnsender.labelStatus.text];
}
@end
