package chess;

import java.util.ArrayList;
import java.util.List;


public class ReWrite {

	private int[][] data;
	private int[][] raw;
	private final int rows=10;
	private final int cols=9;
	private int maxDepth=4;
	private int Goi=-1;
	@SuppressWarnings("unused")
	private int score;
	private int[][][] uchhessValues=new int[8][10][9];
	private int rawScore;

	
	public ReWrite() {
		// TODO Auto-generated constructor stub
		data=new int[10][9];

	}
	
	public ReWrite(int[][] data,int depth){					
		this.maxDepth=depth;
		this.data=data;
		this.raw=BackData(data);
		init();
		this.rawScore=chessValue(this.raw);
	}
	
	public int chessValue(int[][] sub) {					// tinh gia tri ban co
		int nowScore=0;
		for(int i=0;i<rows;i++){							// 1-7 la gia tri cua ng. 8-14 la cua May. 0 la vitri ko co chess
			for(int j=0;j<cols;j++){
				if(sub[i][j]>=8){							//Ham cho may' tinh. nen + diem quan no'. - diem cua quan minh
				
					nowScore+=uchhessValues[sub[i][j]-7][rows-i-1][j];
				}else if(sub[i][j]==0){
					continue;
				}else{
		
					nowScore-=uchhessValues[sub[i][j]][i][j];
				}
			}
		}
		return nowScore;
	}
	
	public void init() {										// diem cua cac quan co tai tung vi tri
		 int[][] ju={{206,208,207,213,214,213,207,208,206},   //xe
				 {206,212,209,216,233,216,209,212,206},
				 {206,208,207,214,216,214,207,208,206},
				 {206,213,213,216,216,216,213,213,206},
				 {208,211,211,214,215,214,211,211,208},
				 {208, 212, 212, 214, 215, 214, 212, 212, 208},
				 {204, 209, 204, 212, 214, 212, 204, 209, 204},
				 {198, 208, 204, 212, 212, 212, 204, 208, 198},
				 {200, 208, 206, 212, 200, 212, 206, 208, 200},
				 {194, 206, 204, 212, 200, 212, 204, 206, 194}
		 };
		 
		 int[][] ma={
		     		{90, 90, 90, 96, 90, 96, 90, 90, 90},
		    		{90, 96,103, 97, 94, 97,103, 96, 90},  //ma
		    		{92, 98, 99,103, 99,103, 99, 98, 92},
		    		{93,108,100,107,100,107,100,108, 93},
		    		{90,100, 99,103,104,103, 99,100, 90},
		    		
		    		{90, 98,101,102,103,102,101, 98, 90},
		    		{92, 94, 98, 95, 98, 95, 98, 94, 92},
		    		{93, 92, 94, 95, 92, 95, 94, 92, 93},
		    		{85, 90, 92, 93, 78, 93, 92, 90, 85},
		    		{88, 85, 90, 88, 90, 88, 90, 85, 88}
		 };
		 
		 int[][] xiang={
		        		{0, 0,20, 0, 0, 0,20, 0, 0}, //tuong
		        		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		        		{18, 0, 0, 0,23, 0, 0, 0, 18},
		        		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		        		{0, 0,20, 0, 0, 0,20, 0, 0},
		        		
		        		{0, 0,20, 0, 0, 0,20, 0, 0},
		        		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		        		{18,0, 0, 0,23, 0, 0, 0,18},
		        		{0, 0, 0, 0, 0, 0, 0, 0, 0}, 
		        		{0, 0,20, 0, 0, 0,20, 0, 0}
		 };
		 
		 int[][] shi={
		        		{0, 0, 0,20, 0,20, 0, 0, 0}, //si
		        		{0, 0, 0, 0,23, 0, 0, 0, 0},
		        		{0, 0, 0,20, 0,20, 0, 0, 0},
		        		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		        		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		        		
		        		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		        		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		        		{0, 0, 0,20, 0,20, 0, 0, 0},
		        		{0, 0, 0, 0,23, 0, 0, 0, 0}, 
		        		{0, 0, 0,20, 0,20, 0, 0, 0}
		 };
		 
		 int[][] jiang={
		          		{0, 0, 0, 8888, 8888, 8888, 0, 0, 0}, //tuong
		        		{0, 0, 0, 8888, 8888, 8888, 0, 0, 0}, 				//danh so diem 5555 de con tuong di xuong chu ko di len
		        		{0, 0, 0, 5555, 5555, 5555, 0, 0, 0},
		        		{0, 0, 0, 0, 0, 0, 0, 0, 0},						//do di len la se la vi tri xau
		        		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		 
		        		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		        		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		        		{0, 0, 0, 5555, 5555, 5555, 0, 0, 0},
		        		{0, 0, 0, 8888, 8888, 8888, 0, 0, 0}, 
		        		{0, 0, 0, 8888, 8888, 8888, 0, 0, 0}
		 };
		 
		 int[][] pao={
		        		{100, 100,  96, 91,  90, 91,  96, 100, 100}, //phao
		        		{98,  98,  96, 92,  89, 92,  96,  98,  98},
		        		{97,  97,  96, 91,  92, 91,  96,  97,  97},
						{96,  99,  99, 98, 100, 98,  99,  99,  96},
		        		 {96,  96,  96, 96, 100, 96,  96,  96,  96}, 
		        		
		        		{95,  96,  99, 96, 100, 96,  99,  96,  95},
		        		 {96,  96,  96, 96,  96, 96,  96,  96,  96},
		        		{97,  96, 100, 99, 101, 99, 100,  96,  97},
		        		{96,  97,  98, 98,  98, 98,  98,  97,  96},
		        		{96,  96,  97, 99,  99, 99,  97,  96,  96}
		 };
		 
		 int[][] bing={
				    {9,  9,  9, 11, 13, 11,  9,  9,  9}, //tot
		    		{19, 24, 34, 42, 44, 42, 34, 24, 19},
		    		{19, 24, 32, 37, 37, 37, 32, 24, 19},
		    		{19, 23, 27, 29, 30, 29, 27, 23, 19},
		    		{14, 18, 20, 27, 29, 27, 20, 18, 14},
		    		
					{7,  0, 13,  0, 16,  0, 13,  0,  7},
		    		{7,  0,  7,  0, 15,  0,  7,  0,  7}, 
		    		{0,  0,  0,  0,  0,  0,  0,  0,  0},
		    		{0,  0,  0,  0,  0,  0,  0,  0,  0},
		    		{0,  0,  0,  0,  0,  0,  0,  0,  0}
		 };
		 
		 int[][] kong={{},{},{},{},{},{},{},{},{},{}};
		 this.uchhessValues[0]=kong;
		 this.uchhessValues[1]=ju;
		 this.uchhessValues[2]=ma;
		 this.uchhessValues[3]=xiang;
		 this.uchhessValues[4]=shi;
		 this.uchhessValues[5]=jiang;
		 this.uchhessValues[6]=pao;
		 this.uchhessValues[7]=bing;
	}
	
	public int[][] work() {														//xu ly nuoc di. luat
		List<List<Integer>> FirstMove=AllMove(data, true);						//ALLmove xet tat ca cac nuoc di co gia tri= true
		int[][] subData=BackData(data);											//truyen du lieu score r xet alpha beta
		this.score=alphabeta(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, data);
		if(Goi==-1){
			for(int i=0;i<FirstMove.get(0).size();i+=2){
				int[][] temp=BackData(subData);
				temp[FirstMove.get(0).get(i+1)][FirstMove.get(1).get(i+1)]=
						temp[FirstMove.get(0).get(i)][FirstMove.get(1).get(i)];
				temp[FirstMove.get(0).get(i)][FirstMove.get(1).get(i)]=0;
				if(!isJiang(temp)){
					Goi=i;
					return temp;
				}
			}
			if(Goi==-1){
				return null;
			}
		}
		subData[FirstMove.get(0).get(Goi+1)][FirstMove.get(1).get(Goi+1)]=
				subData[FirstMove.get(0).get(Goi)][FirstMove.get(1).get(Goi)];
		subData[FirstMove.get(0).get(Goi)][FirstMove.get(1).get(Goi)]=0;
		return subData;
	}
	
	public boolean isJiang(int[][] subuc) {

		int jx = -1;
		int jy = -1;
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				if(subuc[i][j]==12){
					jx=i;
					jy=j;
				}
			}
		}
		if(jx==-1)
			return true;

		for(int h=jy-1;h>=0;h--){
			if(subuc[jx][h]==0)
				continue;
			else if(subuc[jx][h]==1){
				return true;
			}else {
				break;
			}
		}
		for(int h=jy+1;h<cols;h++){
			if(subuc[jx][h]==0)
				continue;
			else if(subuc[jx][h]==1)
				return true;
			else {
				break;
			}
		}
		//���ſ�
		if(jx-1>=0){
			for(int h=jx-1;h>=0;h--){
				if(subuc[h][jy]==0)
					continue;
				else if(subuc[h][jy]==1)
					return true;
				else {
					break;
				}
			}
		}
		for(int h=jx+1;h<rows;h++){
			if(subuc[h][jy]==0)
				continue;
			else if(subuc[h][jy]==1)
				return true;
			else {
				break;
			}
		}
		
		//���Ƿ��ڽ���
		for(int h=jy-1;h>=0;h--){
			if(subuc[jx][h]==0)
				continue;
			else{
				//��һ������
				if(h-1>=0){
					for(int k=h-1;k>=0;k--){
						if(subuc[jx][k]==0)
							continue;
						else if(subuc[jx][k]==6)
							return true;
						else {
							break;
						}
					}
				}
				break;
			}
		}
		for(int h=jy+1;h<cols;h++){
			if(subuc[jx][h]==0)
				continue;
			else{
				if(h+1<cols){
					for(int k=h+1;k<cols;k++){
						if(subuc[jx][k]==0)
							continue;
						else if(subuc[jx][k]==6)
							return true;
						else {
							break;
						}
					}
				}
				break;
			}
		}
		if(jx-1>=0){
			for(int h=jx-1;h>=0;h--){
				if(subuc[h][jy]==0)
					continue;
				else {
					if(h-1>=0){
						for(int k=h-1;k>=0;k--){
							if(subuc[k][jy]==0)
								continue;
							else if(subuc[k][jy]==6)
								return true;
							else {
								break;
							}
						}
					}
					break;
				}
			}
		}
			for(int h=jx+1;h<rows;h++){
				if(subuc[h][jy]==0)
					continue;
				else{
					if(h+1<rows){
						for(int k=h+1;k<rows;k++){
							if(subuc[k][jy]==0)
								continue;
							else if(subuc[k][jy]==6)
								return true;
							else {
								break;
							}
						}
					}
					break;
				}
			}
			

			if(jx-1>=0 && subuc[jx-1][jy+1]==0){
				if((jx-2>=0 && subuc[jx-2][jy+1]==2) || subuc[jx-1][jy+2]==2){
					return true;
				}
			}
		
			if(jx-1>=0 && subuc[jx-1][jy-1]==0){
				if((jx-2>=0 && subuc[jx-2][jy-1]==2) || subuc[jx-1][jy-2]==2){
					return true;
				}
			}
			
			if(subuc[jx+1][jy+1]==0){
				if(subuc[jx+2][jy+1]==2 || subuc[jx+1][jy+2]==2){
					return true;
				}
			}
			
			if(subuc[jx+1][jy-1]==0){
				if(subuc[jx+2][jy-1]==2 || subuc[jx+1][jy-2]==2){
					return true;
				}
			}
			

			for(int h=jx+1;h<rows;h++){
				if(subuc[h][jy]==0)
					continue;
				else if(subuc[h][jy]==5)
					return true;
				else {
					break;
				}
			}
			

			if(subuc[jx+1][jy]==7 || subuc[jx][jy-1]==7 || subuc[jx][jy+1]==7)
				return true;
			
			return false;
		}
	
	public boolean isShuai(int[][] subuc) {

		int jx = -1;
		int jy = -1;
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				if(subuc[i][j]==5){
					jx=i;
					jy=j;
				}
			}
		}
		if(jx==-1)
			return true;

		for(int h=jy-1;h>=0;h--){
			if(subuc[jx][h]==0)
				continue;
			else if(subuc[jx][h]==8){
				return true;
			}else {
				break;
			}
		}
		for(int h=jy+1;h<cols;h++){
			if(subuc[jx][h]==0)
				continue;
			else if(subuc[jx][h]==8)
				return true;
			else {
				break;
			}
		}

			for(int h=jx-1;h>=0;h--){
				if(subuc[h][jy]==0)
					continue;
				else if(subuc[h][jy]==8)
					return true;
				else {
					break;
				}
			}
		
		if(jx+1<rows){
		for(int h=jx+1;h<rows;h++){
			if(subuc[h][jy]==0)
				continue;
			else if(subuc[h][jy]==8)
				return true;
			else {
				break;
			}
		}
		}
		

		for(int h=jy-1;h>=0;h--){
			if(subuc[jx][h]==0)
				continue;
			else{

				if(h-1>=0){
					for(int k=h-1;k>=0;k--){
						if(subuc[jx][k]==0)
							continue;
						else if(subuc[jx][k]==13)
							return true;
						else {
							break;
						}
					}
				}
				break;
			}
		}
		for(int h=jy+1;h<cols;h++){
			if(subuc[jx][h]==0)
				continue;
			else{
				if(h+1<cols){
					for(int k=h+1;k<cols;k++){
						if(subuc[jx][k]==0)
							continue;
						else if(subuc[jx][k]==13)
							return true;
						else {
							break;
						}
					}
				}
				break;
			}
		}

			for(int h=jx-1;h>=0;h--){
				if(subuc[h][jy]==0)
					continue;
				else {
					if(h-1>=0){
						for(int k=h-1;k>=0;k--){
							if(subuc[k][jy]==0)
								continue;
							else if(subuc[k][jy]==13)
								return true;
							else {
								break;
							}
						}
					}
					break;
				}
			}
		
			if(jx+1<rows){
			for(int h=jx+1;h<rows;h++){
				if(subuc[h][jy]==0)
					continue;
				else{
					if(h+1<rows){
						for(int k=h+1;k<rows;k++){
							if(subuc[k][jy]==0)
								continue;
							else if(subuc[k][jy]==13)
								return true;
							else {
								break;
							}
						}
					}
					break;
				}
			}
			}
			
			//���Ƿ�����
			if(subuc[jx-1][jy+1]==0){
				if(subuc[jx-2][jy+1]==9 || subuc[jx-1][jy+2]==9){
					return true;
				}
			}
		
			if(subuc[jx-1][jy-1]==0){
				if(subuc[jx-2][jy-1]==9 || subuc[jx-1][jy-2]==9){
					return true;
				}
			}
			
			if(jx+1<rows && subuc[jx+1][jy+1]==0){
				if((jx+2<rows && subuc[jx+2][jy+1]==9) || subuc[jx+1][jy+2]==9){
					return true;
				}
			}
			
			if(jx+1<rows && subuc[jx+1][jy-1]==0){
				if((jx+2<rows && subuc[jx+2][jy-1]==9) || subuc[jx+1][jy-2]==9){
					return true;
				}
			}
			for(int h=jx-1;h>=0;h--){
				if(subuc[h][jy]==0)
					continue;
				else if(subuc[h][jy]==12)
					return true;
				else {
					break;
				}
			}
		
			if(subuc[jx-1][jy]==14 || subuc[jx][jy-1]==14 || subuc[jx][jy+1]==14)
				return true;
			
			return false;
	}
	
	//alphabeta
	public int alphabeta(int alpha,int beta,int depth,int[][] data) {
		if(depth==maxDepth)										//tra lai gia tri ban co o depth =maxdepth
			return evluate(data);
																// neu ko =maxdepth thi Luu list allmove o do sau nay
		List<List<Integer>> tmp=AllMove(data, depth%2==0?true:false);	
		int best=depth%2==0?-Integer.MIN_VALUE:Integer.MAX_VALUE;	//depth%2 de xet xem ng hay may'neu la may' thi no ms lay gtri
																	//best la gia tri tot nhat da co
		for(int i=0;i<tmp.get(0).size();i+=2){
			int ox=tmp.get(0).get(i);
			int oy=tmp.get(1).get(i);
			int nx=tmp.get(0).get(i+1);
			int ny=tmp.get(1).get(i+1);
			int[][] sub=BackData(data);
			sub[ox][oy]=0;
			sub[nx][ny]=data[ox][oy];
			if(depth%2==0){
				if(isJiang(sub)){
					continue;
				}
			}else{
				if(isShuai(sub)){
					continue;
				}
			}
			int value=alphabeta(alpha, beta, depth+1, sub);	//xet tiep khi depth!= maxdepth
			if(depth%2==0){
				//max
				if(value>best){								//so sanh best vs value. value la gia tri htai. neu tot thi thay best=value
					best=value;
					alpha=value;
					if(depth==0)
						Goi=i;
				}
			}else{
				//min
				if(value<best){
					best=value;
					beta=value;
				}
			}
			if(alpha>=beta)
				return best;
		}
		return best;
	}
	
	
	public int[][] BackData(int[][] data) {				//xet gia tri a-b tren bien sub phu. chi khi tra ve ms tra ve du lieu chinh
		int[][] sub=new int[data.length][data[0].length];
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data[0].length;j++){
				sub[i][j]=data[i][j];
			}
		}
		return sub;
	}
	
	public int evluate(int[][] sub) {
	
		return chessValue(sub)-rawScore;
	}
	
	
	public List<List<Integer>> AllMove(int[][] data,boolean isAI) {		//xet tat ca cac nc di, xem true hay false
		List<List<Integer>> total=new ArrayList<>();
		total.add(new ArrayList<Integer>());
		total.add(new ArrayList<Integer>());
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				if(isAI){
					if(data[i][j]>=8){
						List<List<Integer>> part=canMove(data, i, j, data[i][j]);
						total.get(0).addAll(part.get(0));
						total.get(1).addAll(part.get(1));
					}
				}else{
					if(data[i][j]>0 && data[i][j]<=7){
						List<List<Integer>> part=canMove(data, i, j, data[i][j]);
						total.get(0).addAll(part.get(0));
						total.get(1).addAll(part.get(1));
					}
				}
			}
		}
		return total;
	}
	
private List<List<Integer>> canMove(int[][] data,int i,int j,int label) {	//xet ham canmove cho tung quan co. theo case
																			// luat choi. cach danh  
	//���ֿ����ƶ��ĵ�
	java.util.List<Integer> listx=new ArrayList<>();
	java.util.List<Integer> listy=new ArrayList<>();
	switch (label) {
	case 8:
	case 1:

		if(j-1>=0){
		for(int h=j-1;h>=0;h--){
				if(data[i][h]==0){
					listx.add(i);
					listy.add(j);
					listx.add(i);
					listy.add(h);
				}else if((label<=7 && data[i][h]>=8) || (label>=8 && data[i][h]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(i);
					listy.add(h);
					break;
				}else{
					
					break;
				}
			}
		}
		
		
		if(j+1<cols){
			for(int h=j+1;h<cols;h++){
				if(data[i][h]==0){
					listx.add(i);
					listy.add(j);
					listx.add(i);
					listy.add(h);
				}else if((label<=7 && data[i][h]>=8) || (label>=8 && data[i][h]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(i);
					listy.add(h);
					break;
				}else {
					break;
				}
			}
		}


		if(i-1>=0){
			for(int k=i-1;k>=0;k--){
				if(data[k][j]==0){
					listx.add(i);
					listy.add(j);
					listx.add(k);
					listy.add(j);
				}else if((label<=7 && data[k][j]>=8) || (label>=8 && data[k][j]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(k);
					listy.add(j);
					break;
				}else {
					break;
				}
			}
		}

		if(i+1<rows){
			for(int k=i+1;k<rows;k++){
				if(data[k][j]==0){
					listx.add(i);
					listy.add(j);
					listx.add(k);
					listy.add(j);
				}else if((label<=7 && data[k][j]>=8) || (label>=8 && data[k][j]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(k);
					listy.add(j);
					break;
				}else {
					break;
				}
			}
		}
		break;
		
	case 6:
	case 13:
		//����
		if(j-1>=0){
			for(int h=j-1;h>=0;h--){
				if(data[i][h]==0){
					listx.add(i);
					listy.add(j);
					listx.add(i);
					listy.add(h);
				}else {
					if(h-1>=0){
						for(int k=h-1;k>=0;k--){
							if(data[i][k]==0){
								continue;
							}
							else if((label<=7 && data[i][k]>=8)||(label>=8 && data[i][k]<=7)){
								listx.add(i);
								listy.add(j);
								listx.add(i);
								listy.add(k);
								break;
							}else{
								break;
							}
						}
					}
					break;
				}
			}
		}
		
		if(j+1<cols){
			for(int h=j+1;h<cols;h++){
				if(data[i][h]==0){
					listx.add(i);
					listy.add(j);
					listx.add(i);
					listy.add(h);
				}else{
					if(h+1<cols){
						for(int k=h+1;k<cols;k++){
							if(data[i][k]==0)
								continue;
							else if((label<=7 && data[i][k]>=8)||(label>=8 && data[i][k]<=7)){
								listx.add(i);
								listy.add(j);
								listx.add(i);
								listy.add(k);
								break;
							}else {
								break;
							}
						}
					}
					break;
				}
			}
		}
		
		if(i-1>=0){
			for(int h=i-1;h>=0;h--){
				if(data[h][j]==0){
					listx.add(i);
					listy.add(j);
					listx.add(h);
					listy.add(j);
				}else{
					if(h-1>=0){
						for(int k=h-1;k>=0;k--){
							if(data[k][j]==0)
								continue;
							else if((label<=7 && data[k][j]>=8)||(label>=8 && data[k][j]<=7)){
								listx.add(i);
								listy.add(j);
								listx.add(k);
								listy.add(j);
								break;
							}else {
								break;
							}
						}
					}
					break;
				}
			}
		}
		
		if(i+1<rows){
			for(int h=i+1;h<rows;h++){
				if(data[h][j]==0){
					listx.add(i);
					listy.add(j);
					listx.add(h);
					listy.add(j);
				}else{
					if(h+1<cols){
						for(int k=h+1;k<rows;k++){
							if(data[k][j]==0)
								continue;
							else if((label<=7 && data[k][j]>=8)||(label>=8 && data[k][j]<=7)){
								listx.add(i);
								listy.add(j);
								listx.add(k);
								listy.add(j);
								break;
							}else {
								break;
							}
						}
					}
					break;
				}
			}
		}
		break;
		
	case 2:
	case 9:
		//����
		//��������
		if(i-2>=0 && j+1<cols){
			if(data[i-1][j]==0){
				if(data[i-2][j+1]==0 || (label<=7 && data[i-2][j+1]>=8) || (label>=8 && data[i-2][j+1]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(i-2);
					listy.add(j+1);
				}
			}
		}
		//������
		if(i-1>=0 && j+2<cols){
			if(data[i][j+1]==0){
				if(data[i-1][j+2]==0 || (label<=7 && data[i-1][j+2]>=8) || (label>=8 && data[i-1][j+2]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(i-1);
					listy.add(j+2);
				}
			}
		}
		//������
		if(i+1<rows && j+2<cols){
			if(data[i][j+1]==0){
				if(data[i+1][j+2]==0 || (label<=7 && data[i+1][j+2]>=8) || (label>=8 && data[i+1][j+2]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(i+1);
					listy.add(j+2);
				}
			}
		}
		//������
		if(i+2<rows && j+1<cols){
			if(data[i+1][j]==0){
				if(data[i+2][j+1]==0 || (label<=7 && data[i+2][j+1]>=8) || (label>=8 && data[i+2][j+1]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(i+2);
					listy.add(j+1);
				}
			}
		}
		//������
		if(i+1<rows && j-2>=0){
			if(data[i][j-1]==0){
				if(data[i+1][j-2]==0 || (label<=7 && data[i+1][j-2]>=8) || (label>=8 && data[i+1][j-2]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(i+1);
					listy.add(j-2);
				}
			}
		}
		//������
		if(i+2<rows && j-1>=0){
			if(data[i+1][j]==0){
				if(data[i+2][j-1]==0 || (label<=7 && data[i+2][j-1]>=8) || (label>=8 && data[i+2][j-1]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(i+2);
					listy.add(j-1);
				}
			}
		}
		//������
		if(i-1>=0 && j-2>=0){
			if(data[i][j-1]==0){
				if(data[i-1][j-2]==0 || (label<=7 && data[i-1][j-2]>=8) || (label>=8 && data[i-1][j-2]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(i-1);
					listy.add(j-2);
				}
			}
		}
		//������
		if(i-2>=0 && j-1>=0){
			if(data[i-1][j]==0){
				if(data[i-2][j-1]==0 || (label<=7 && data[i-2][j-1]>=8) || (label>=8 && data[i-2][j-1]<=7)){
					listx.add(i);
					listy.add(j);
					listx.add(i-2);
					listy.add(j-1);// ��ͬ��
				}
			}
		}
		break;
		
	case 10:

		if(i-2>=0 && j+2<cols){
			if(data[i-1][j+1]==0){
				if(data[i-2][j+2]<=7){
					listx.add(i);
					listy.add(j);
					listx.add(i-2);
					listy.add(j+2);
				}
			}
		}
		
		if(i+2<rows/2 && j+2<cols){
			if(data[i+1][j+1]==0){
				if(data[i+2][j+2]<=7){
					listx.add(i);
					listy.add(j);
					listx.add(i+2);
					listy.add(j+2);
				}
			}
		}
	
		if(i+2<rows/2 && j-2>=0){
			if(data[i+1][j-1]==0){
				if(data[i+2][j-2]<=7){
					listx.add(i);
					listy.add(j);
					listx.add(i+2);
					listy.add(j-2);
				}
			}
		}
		//����
		if(i-2>=0 && j-2>=0){
			if(data[i-1][j-1]==0){
				if(data[i-2][j-2]<=7){
					listx.add(i);
					listy.add(j);
					listx.add(i-2);
					listy.add(j-2);
				}
			}
		}
		break;
	case 3:

		if(i-2>=rows/2 && j+2<cols){
			if(data[i-1][j+1]==0){
				if(data[i-2][j+2]==0 ||  data[i-2][j+2]>=8){
					listx.add(i);
					listy.add(j);
					listx.add(i-2);
					listy.add(j+2);
				}
			}
		}

		if(i+2<rows && j+2<cols){
			if(data[i+1][j+1]==0){
				if(data[i+2][j+2]==0 || data[i+2][j+2]>=8){
					listx.add(i);
					listy.add(j);
					listx.add(i+2);
					listy.add(j+2);
				}
			}
		}

		if(i+2<rows && j-2>=0){
			if(data[i+1][j-1]==0){
				if(data[i+2][j-2]==0 || data[i+2][j-2]>=8){
					listx.add(i);
					listy.add(j);
					listx.add(i+2);
					listy.add(j-2);
				}
			}
		}
		//����
		if(i-2>=rows/2 && j-2>=0){
			if(data[i-1][j-1]==0){
				if(data[i-2][j-2]==0 || data[i-2][j-2]>=8){
					listx.add(i);
					listy.add(j);
					listx.add(i-2);
					listy.add(j-2);
				}
			}
		}
		break;
	case 4:
		//ʿ
		//����
		if(i-1>=7 && j+1<=5){
			if(data[i-1][j+1]==0 || data[i-1][j+1]>=8){
				listx.add(i);
				listy.add(j);
				listx.add(i-1);
				listy.add(j+1);
			}
		}
		//����
		if(i+1<rows && j+1<=5){
			if(data[i-1][j+1]==0 || data[i-1][j+1]>=8){
				listx.add(i);
				listy.add(j);
				listx.add(i+1);
				listy.add(j+1);
			}
		}
		//����
		if(i+1<rows && j-1>=3){
			if(data[i+1][j-1]==0 || data[i+1][j-1]>=8){
				listx.add(i);
				listy.add(j);
				listx.add(i+1);
				listy.add(j-1);
			}
		}
		//����
		if(i-1>=7 && j-1>=3){
			if(data[i-1][j-1]==0 || data[i-1][j-1]>=8){
				listx.add(i);
				listy.add(j);
				listx.add(i-1);
				listy.add(j-1);
			}
		}
		break;
	case 11:
		//��ɫʿ
		//����
		if(i-1>=0 && j+1<=5){
			if(data[i-1][j+1]<=7){
				listx.add(i);
				listy.add(j);
				listx.add(i-1);
				listy.add(j+1);
			}
		}
		//����
		if(i+1<=2 && j+1<=5){
			if(data[i+1][j+1]<=7){
				listx.add(i);
				listy.add(j);
				listx.add(i+1);
				listy.add(j+1);
			}
		}
		//����
		if(i+1<=2 && j-1>=3){
			if(data[i+1][j-1]<=7){
				listx.add(i);
				listy.add(j);
				listx.add(i+1);
				listy.add(j-1);
			}
		}
		//����
		if(i-1>=0 && j-1>=3){
			if(data[i-1][j-1]<=7){
				listx.add(i);
				listy.add(j);
				listx.add(i-1);
				listy.add(j-1);
			}
		}
		break;
	case 5:
		//˧
		//��
		if(i-1<=7){
			if(data[i-1][j]==0 || data[i-1][j]>=8){
				listx.add(i);
				listy.add(j);
				listx.add(i-1);
				listy.add(j);
			}
		}
		//��
		if(i+1<rows){
			if(data[i+1][j]==0 || data[i+1][j]>=8){
				listx.add(i);
				listy.add(j);
				listx.add(i+1);
				listy.add(j);
			}
		}
		//��
		if(j-1>=3){
			if(data[i][j-1]==0 || data[i][j-1]>=8){
				listx.add(i);
				listy.add(j);
				listx.add(i);
				listy.add(j-1);
			}
		}
		//��
		if(j+1<=5){
			if(data[i][j+1]==0 || data[i][j+1]>=8){
				listx.add(i);
				listy.add(j);
				listx.add(i);
				listy.add(j+1);
			}
		}
		break;
	case 12:
		//��
		//��
		if(i-1>=0){
			if(data[i-1][j]<=7){
				listx.add(i);
				listy.add(j);
				listx.add(i-1);
				listy.add(j);
			}
		}
		//��
		if(i+1<=2){
			if(data[i+1][j]<=7){
				listx.add(i);
				listy.add(j);
				listx.add(i+1);
				listy.add(j);
			}
		}
		//��
		if(j-1>=3){
			if(data[i][j-1]<=7){
				listx.add(i);
				listy.add(j);
				listx.add(i);
				listy.add(j-1);
			}
		}
		//��
		if(j+1<=5){
			if(data[i][j+1]<=7){
				listx.add(i);
				listy.add(j);
				listx.add(i);
				listy.add(j+1);
			}
		}
		break;
	case 7:
		//��
		if(i>4){
			//û�й���
			if(data[i-1][j]==0 || data[i-1][j]>=8){
				listx.add(i);
				listy.add(j);
				listx.add(i-1);
				listy.add(j);
			}
		}else{
			//��
			if(i-1>=0){
				if(data[i-1][j]==0 || data[i-1][j]>=8){
					listx.add(i);
					listy.add(j);
					listx.add(i-1);
					listy.add(j);
				}
			}
			//��
			if(j-1>=0){
				if(data[i][j-1]==0 || data[i][j-1]>=8){
					listx.add(i);
					listy.add(j);
					listx.add(i);
					listy.add(j-1);
				}
			}
			//��
			if(j+1<cols){
				if(data[i][j+1]==0 || data[i][j+1]>=8){
					listx.add(i);
					listy.add(j);
					listx.add(i);
					listy.add(j+1);
				}
			}
		}
		break;
	case 14:
		//��
		if(i<5){
			//û�й���
			if(data[i+1][j]<=7){
				listx.add(i);
				listy.add(j);
				listx.add(i+1);
				listy.add(j);
			}
		}else{
			//��
			if(i+1<rows){
				if(data[i+1][j]<=7){
					listx.add(i);
					listy.add(j);
					listx.add(i+1);
					listy.add(j);
				}
			}
			//��
			if(j-1>=0){
				if(data[i][j-1]<=7){
					listx.add(i);
					listy.add(j);
					listx.add(i);
					listy.add(j-1);
				}
			}
			//��
			if(j+1<cols){
				if(data[i][j+1]<=7){
					listx.add(i);
					listy.add(j);
					listx.add(i);
					listy.add(j+1);
				}
			}
		}
		break;
	default:
		break;
	}
	List<List<Integer>> res=new ArrayList<>();
	res.add(listx);
	res.add(listy);
	return res;
  }
}
