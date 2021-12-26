package chess;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;

public class Chess {
	private int[][] data;
	private UChess root;
	private int toGo;
	private final int rows=10;
	private final int cols=9;
	private int[] ucvalue={10,5,3,2,100,8,2};
	private boolean isAI;
	
	public Chess() {
		// TODO Auto-generated constructor stub
	}
	
	public Chess(int[][] subData){
		data=BackData(subData);                  //du lieu data ban co hien tai. luu vi tri cac quan co
	}
	
	public int[][] compute(int depth) {    // ktra con Tuong hay ko 00:43. neu mat thi goi den ChessDraw hien thi sorry,you lose
		hasWin(data);
		boolean isCon=false;
		for(int i=7;i<=9;i++)
			for(int j=3;j<=5;j++)
				if(data[i][j]==5){		
					isCon=true;					//isCon la bien de check Tuong con hay ko
					break;
				}
		if(isCon){
			return new ReWrite(data,depth).work();		//con thi goi Rewrite truyen vao gia tri cua Data, depth
		}
		else{
			return data;
		}
	}
	
	public void hasWin(int[][] data) {
		for(int i=0;i<=9;i++){
			for(int j=0;j<=8;j++){
				int label=data[i][j];
				switch (label) {
				case 8:
					//�ǳ�
					for(int h=i+1;h<rows;h++){
						if(data[h][j]==0)
							continue;
						else if(data[h][j]!=5)
							break;
						else{
							//��˧
							data[i][j]=0;
							data[h][j]=label;
							break;
						}
					}
					for(int h=i-1;h>=0;h--){
						if(data[h][j]==0)
							continue;
						else if(data[h][j]!=5)
							break;
						else{
							data[i][j]=0;
							data[h][j]=label;
							break;
						}
					}
					for(int h=j-1;h>=0;h--){
						if(data[i][h]==0)
							continue;
						else if(data[i][h]!=5)
							break;
						else {
							data[i][j]=0;
							data[i][h]=label;
						}
					}
					for(int h=j+1;h<cols;h++){
						if(data[i][h]==0)
							continue;
						else if(data[i][h]!=5)//д��,��д��2
							break;
						else {
							data[i][j]=0;
							data[i][h]=label;
							break;
						}
					}
					break;
				case 13:
					//����
					for(int h=i+1;h<rows;h++){
						if(data[h][j]==0)
							continue;
						else{
							//��һ����
							for(int k=h+1;k<rows;k++){
								if(data[k][j]==0)
									continue;
								else if(data[k][j]!=5)
									break;
								else {
									data[i][j]=0;
									data[k][j]=label;
									break;
								}
							}
							break;
						}
					}
					for(int h=i-1;h>=0;h--){
						if(data[h][j]==0)
							continue;
						else{
							for(int k=h-1;k>=0;k--){
								if(data[k][j]==0)
									continue;
								else if(data[k][j]!=5)
									break;
								else {
									data[i][j]=0;
									data[k][j]=label;
									break;
								}
							}
							break;
						}
					}
					for(int h=j-1;h>=0;h--){
						if(data[i][h]==0)
							continue;
						else{
							for(int k=h-1;k>=0;k--){
								if(data[i][k]==0)
									continue;
								else if(data[i][k]!=5)
									break;
								else {
									data[i][j]=0;
									data[i][k]=label;
									break;
								}
							}
							break;
						}
					}
					for(int h=j+1;h<cols;h++){
						if(data[i][h]==0)
							continue;
						else{
							for(int k=h+1;k<cols;k++){
								if(data[i][k]==0)
									continue;
								else if(data[i][k]!=5)
									break;
								else {
									data[i][j]=0;
									data[i][k]=label;
									break;
								}
							}
							break;
						}
					}
					break;
				case 9:
					//��
					if(i-1>=0 && data[i-1][j]==0){
						if(i-2>=0 && j+1<cols && data[i-2][j+1]==5){
							data[i][j]=0;
							data[i-2][j+1]=label;
						}else if(i-2>=0 && j-1>=0 && data[i-2][j-1]==5){
							data[i][j]=0;
							data[i-2][j-1]=label;
						}
					}
					if(j+1<cols && data[i][j+1]==0){
						if(i-1>=0 && j+2<cols && data[i-1][j+2]==5){
							data[i][j]=0;
							data[i-1][j+2]=label;
						}else if(i+1<rows && j+2<cols && data[i+1][j+2]==5){
							data[i][j]=0;
							data[i+1][j+2]=label;
						}
					}
					if(i+1<rows && data[i+1][j]==0){
						if(i+2<rows && j+1<cols && data[i+2][j+1]==5){
							data[i][j]=0;
							data[i+2][j+1]=label;
						}else if(i+2<rows && j-1>=0 && data[i+2][j-1]==5){
							data[i][j]=0;
							data[i+2][j-1]=label;
						}
					}
					if(j-1>=0 && data[i][j-1]==0){
						if(i-1>=0 && j-2>=0 && data[i-1][j-2]==5){
							data[i][j]=0;
							data[i-1][j-2]=label;
						}else if(i+1<rows && j-2>=0 && data[i+1][j-2]==5){
							data[i][j]=0;
							data[i+1][j-2]=label;
						}
					}
					break;
				case 12:
					//��
					for(int h=i+1;h<rows;h++){
						if(data[h][j]==0)
							continue;
						else if(data[h][j]!=5)
							break;
						else {
							data[i][j]=0;
							data[h][j]=label;
							break;
						}
					}
					break;
				case 14:
					//��
					if(i+1<rows && data[i+1][j]==5){
						data[i][j]=0;
						data[i+1][j]=label;
					}else if(j-1>=0 && data[i][j-1]==5){
						data[i][j]=0;
						data[i][j-1]=label;
					}else if(j+1<cols && data[i][j+1]==5){
						data[i][j]=0;
						data[i][j+1]=label;
					}
					break;
				default:
					break;
				}
			}
		}
	}
	

	public void BestMove() {
		
		root=new UChess();
		root.depth=0;
		root.innerData=BackData(data);
		Queue<UChess> all=new LinkedList<Chess.UChess>();
		int count=0;
		all.offer(root);
		while(!all.isEmpty()){
			System.out.println(++count);
			UChess uc=all.poll();

			for(int i=0;i<uc.chidern.size();i++){
				all.offer(uc.chidern.get(i));
				}
			}
		if(count>=200000)
			return;
		System.exit(1);
		complishSelf(root);
		writeValue(root, root.chidern);
	}
	
	
	
	public void arrange(int[][] canPos,UChess uc,ArrayList<UChess> Cthis,int depth,int ir,int jc,int label) {
		for(int i=0;i<canPos.length;i++){
			int[][] subData=BackData(uc.innerData);
			subData[ir][jc]=0;
			subData[canPos[i][0]][canPos[i][1]]=label;
			if((isJiang(subData) && isAI) || (isShuai(subData) && !isAI))
				continue;
			UChess subuc=new UChess();
			subuc.parent=uc;
			subuc.depth=uc.depth+1;
			subuc.innerData=subData;
			Cthis.add(subuc);
		}
	}
	
	public boolean isJiang(int[][] subuc) {
		//���Ƿ񱻳�����
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
		

		for(int h=jy-1;h>=0;h--){
			if(subuc[jx][h]==0)
				continue;
			else{
			
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
			

			if(subuc[jx-1][jy+1]==0){
				if(subuc[jx-2][jy+1]==2 || subuc[jx-1][jy+2]==2){
					return true;
				}
			}
		
			if(subuc[jx-1][jy-1]==0){
				if(subuc[jx-2][jy-1]==2 || subuc[jx-1][jy-2]==2){
					return true;
				}
			}
			
			if(jx+1<rows && subuc[jx+1][jy+1]==0){
				if((jx+2<rows && subuc[jx+2][jy+1]==2) || subuc[jx+1][jy+2]==2){
					return true;
				}
			}
			
			if(jx+1<rows && subuc[jx+1][jy-1]==0){
				if((jx+2<rows && subuc[jx+2][jy-1]==2) || subuc[jx+1][jy-2]==2){
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
	
	public int evalute(UChess uc) {

		int[] init=new int[15];
		int[] now=new int[15];
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				init[data[i][j]]++;
			}
		}
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				now[uc.innerData[i][j]]++;
			}
		}
		
		int value=ucvalue[0]*(now[8]-init[8])+ucvalue[1]*(now[9]-init[9])+ucvalue[2]*(now[10]-init[10])+ucvalue[3]*(now[11]-init[11])+
				ucvalue[4]*(now[12]-init[12])
				+ucvalue[5]*(now[13]-init[13])+ucvalue[6]*(now[14]-init[14])-ucvalue[0]*(now[1]-init[1])-ucvalue[1]*(now[2]-init[2])-
				ucvalue[2]*(now[3]-init[3])-ucvalue[3]*(now[4]-init[4])
				-ucvalue[4]*(now[5]-init[5])-ucvalue[5]*(now[6]-init[6])-ucvalue[6]*(now[7]-init[7]);
		return value;
	}
	
	private int[][] findPos(int[][] data,int label) {
		int[][] pos=new int[5][2];
		for(int i=0;i<pos.length;i++)
			pos[i][0]=-1;
		int more=0;
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				if(data[i][j]==label){
					pos[more][0]=i;
					pos[more++][1]=j;
				}
			}
		}
		return pos;
	}
	
	private int[][] canMove(int[][] data,int i,int j,int label) {

		java.util.List<Integer> listx=new ArrayList<>();
		java.util.List<Integer> listy=new ArrayList<>();
		switch (label) {
		case 8:
		case 1:

			if(j-1>=0){
			for(int h=j-1;h>=0;h--){
					if(data[i][h]==0){
						listx.add(i);
						listy.add(h);
					}else if((label<=7 && data[i][h]>=8) || (label>=8 && data[i][h]<=7)){
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
						listy.add(h);
					}else if((label<=7 && data[i][h]>=8) || (label>=8 && data[i][h]<=7)){
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
						listx.add(k);
						listy.add(j);
					}else if((label<=7 && data[k][j]>=8) || (label>=8 && data[k][j]<=7)){
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
						listx.add(k);
						listy.add(j);
					}else if((label<=7 && data[k][j]>=8) || (label>=8 && data[k][j]<=7)){
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
						listy.add(h);
					}else {
						if(h-1>=0){
							for(int k=h-1;k>=0;k--){
								if(data[i][k]==0){
									continue;
								}
								else if((label<=7 && data[i][k]>=8)||(label>=8 && data[i][k]<=7)){
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
						listy.add(h);
					}else{
						if(h+1<cols){
							for(int k=h+1;k<cols;k++){
								if(data[i][k]==0)
									continue;
								else if((label<=7 && data[i][k]>=8)||(label>=8 && data[i][k]<=7)){
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
						listx.add(h);
						listy.add(j);
					}else{
						if(h-1>=0){
							for(int k=h-1;k>=0;k--){
								if(data[k][j]==0)
									continue;
								else if((label<=7 && data[k][j]>=8)||(label>=8 && data[k][j]<=7)){
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
						listx.add(h);
						listy.add(j);
					}else{
						if(h+1<cols){
							for(int k=h+1;k<rows;k++){
								if(data[k][j]==0)
									continue;
								else if((label<=7 && data[k][j]>=8)||(label>=8 && data[k][j]<=7)){
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

			if(i-2>=0 && j+1<cols){
				if(data[i-1][j]==0){
					if(data[i-2][j+1]==0 || (label<=7 && data[i-2][j+1]>=8) || (label>=8 && data[i-2][j+1]<=7)){
						listx.add(i-2);
						listy.add(j+1);
					}
				}
			}

			if(i-1>=0 && j+2<cols){
				if(data[i][j+1]==0){
					if(data[i-1][j+2]==0 || (label<=7 && data[i-1][j+2]>=8) || (label>=8 && data[i-1][j+2]<=7)){
						listx.add(i-1);
						listy.add(j+2);
					}
				}
			}

			if(i+1<rows && j+2<cols){
				if(data[i][j+1]==0){
					if(data[i+1][j+2]==0 || (label<=7 && data[i+1][j+2]>=8) || (label>=8 && data[i+1][j+2]<=7)){
						listx.add(i+1);
						listy.add(j+2);
					}
				}
			}
		
			if(i+2<rows && j+1<cols){
				if(data[i+1][j]==0){
					if(data[i+2][j+1]==0 || (label<=7 && data[i+2][j+1]>=8) || (label>=8 && data[i+2][j+1]<=7)){
						listx.add(i+2);
						listy.add(j+1);
					}
				}
			}
		
			if(i+1<rows && j-2>=0){
				if(data[i][j-1]==0){
					if(data[i+1][j-2]==0 || (label<=7 && data[i+1][j-2]>=8) || (label>=8 && data[i+1][j-2]<=7)){
						listx.add(i+1);
						listy.add(j-2);
					}
				}
			}
		
			if(i+2<rows && j-1>=0){
				if(data[i+1][j]==0){
					if(data[i+2][j-1]==0 || (label<=7 && data[i+2][j-1]>=8) || (label>=8 && data[i+2][j-1]<=7)){
						listx.add(i+2);
						listy.add(j-1);
					}
				}
			}
	
			if(i-1>=0 && j-2>=0){
				if(data[i][j-1]==0){
					if(data[i-1][j-2]==0 || (label<=7 && data[i-1][j-2]>=8) || (label>=8 && data[i-1][j-2]<=7)){
						listx.add(i-1);
						listy.add(j-2);
					}
				}
			}
			
			if(i-2>=0 && j-1>=0){
				if(data[i-1][j]==0){
					if(data[i-2][j-1]==0 || (label<=7 && data[i-2][j-1]>=8) || (label>=8 && data[i-2][j-1]<=7)){
						listx.add(i-2);
						listy.add(j-1);//��ͬ��
					}
				}
			}
			break;
			
		case 10:

			if(i-2>=0 && j+2<cols){
				if(data[i-1][j+1]==0){
					if(data[i-2][j+2]<=7){
						listx.add(i-2);
						listy.add(j+2);
					}
				}
			}
		
			if(i+2<rows/2 && j+2<cols){
				if(data[i+1][j+1]==0){
					if(data[i+2][j+2]<=7){
						listx.add(i+2);
						listy.add(j+2);
					}
				}
			}
			
			if(i+2<rows/2 && j-2>=0){
				if(data[i+1][j-1]==0){
					if(data[i+2][j-2]<=7){
						listx.add(i+2);
						listy.add(j-2);
					}
				}
			}
	
			if(i-2>=0 && j-2>=0){
				if(data[i-1][j-1]==0){
					if(data[i-2][j-2]<=7){
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
						listx.add(i-2);
						listy.add(j+2);
					}
				}
			}
			//����
			if(i+2<rows && j+2<cols){
				if(data[i+1][j+1]==0){
					if(data[i+2][j+2]==0 || data[i+2][j+2]>=8){
						listx.add(i+2);
						listy.add(j+2);
					}
				}
			}
			//����
			if(i+2<rows && j-2>=0){
				if(data[i+1][j-1]==0){
					if(data[i+2][j-2]==0 || data[i+2][j-2]>=8){
						listx.add(i+2);
						listy.add(j-2);
					}
				}
			}
			//����
			if(i-2>=rows/2 && j-2>=0){
				if(data[i-1][j-1]==0){
					if(data[i-2][j-2]==0 || data[i-2][j-2]>=8){
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
					listx.add(i-1);
					listy.add(j+1);
				}
			}
			//����
			if(i+1<rows && j+1<=5){
				if(data[i-1][j+1]==0 || data[i-1][j+1]>=8){
					listx.add(i+1);
					listy.add(j+1);
				}
			}
			//����
			if(i+1<rows && j-1>=3){
				if(data[i+1][j-1]==0 || data[i+1][j-1]>=8){
					listx.add(i+1);
					listy.add(j-1);
				}
			}
			//����
			if(i-1>=7 && j-1>=3){
				if(data[i-1][j-1]==0 || data[i-1][j-1]>=8){
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
					listx.add(i-1);
					listy.add(j+1);
				}
			}
			//����
			if(i+1<=2 && j+1<=5){
				if(data[i+1][j+1]<=7){
					listx.add(i+1);
					listy.add(j+1);
				}
			}
			//����
			if(i+1<=2 && j-1>=3){
				if(data[i+1][j-1]<=7){
					listx.add(i+1);
					listy.add(j-1);
				}
			}
			//����
			if(i-1>=0 && j-1>=3){
				if(data[i-1][j-1]<=7){
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
					listx.add(i-1);
					listy.add(j);
				}
			}
			//��
			if(i+1<rows){
				if(data[i+1][j]==0 || data[i+1][j]>=8){
					listx.add(i+1);
					listy.add(j);
				}
			}
			//��
			if(j-1>=3){
				if(data[i][j-1]==0 || data[i][j-1]>=8){
					listx.add(i);
					listy.add(j-1);
				}
			}
			//��
			if(j+1<=5){
				if(data[i][j+1]==0 || data[i][j+1]>=8){
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
					listx.add(i-1);
					listy.add(j);
				}
			}
			//��
			if(i+1<=2){
				if(data[i+1][j]<=7){
					listx.add(i+1);
					listy.add(j);
				}
			}
			//��
			if(j-1>=3){
				if(data[i][j-1]<=7){
					listx.add(i);
					listy.add(j-1);
				}
			}
			//��
			if(j+1<=5){
				if(data[i][j+1]<=7){
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
					listx.add(i-1);
					listy.add(j);
				}
			}else{
				//��
				if(i-1>=0){
					if(data[i-1][j]==0 || data[i-1][j]>=8){
						listx.add(i-1);
						listy.add(j);
					}
				}
				//��
				if(j-1>=0){
					if(data[i][j-1]==0 || data[i][j-1]>=8){
						listx.add(i);
						listy.add(j-1);
					}
				}
				//��
				if(j+1<cols){
					if(data[i][j+1]==0 || data[i][j+1]>=8){
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
					listx.add(i+1);
					listy.add(j);
				}
			}else{
				//��
				if(i+1<rows){
					if(data[i+1][j]<=7){
						listx.add(i+1);
						listy.add(j);
					}
				}
				//��
				if(j-1>=0){
					if(data[i][j-1]<=7){
						listx.add(i);
						listy.add(j-1);
					}
				}
				//��
				if(j+1<cols){
					if(data[i][j+1]<=7){
						listx.add(i);
						listy.add(j+1);
					}
				}
			}
			break;
		default:
			break;
		}
		int len=listx.size();
		int[][] furMov=new int[len][2];
		for(int h=0;h<len;h++){
			furMov[h][0]=listx.get(h);
			furMov[h][1]=listy.get(h);
		}
		return furMov;
	}
	
	public int[][] BackData(int[][] data) {
		int[][] sub=new int[data.length][data[0].length];
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data[0].length;j++){
				sub[i][j]=data[i][j];//��ȸ���
			}
		}
		return sub;
	}
	
	public void complishSelf(UChess ch) {
		
		for(int i=0;i<ch.chidern.size();i++){
			if(ch.chidern.get(i).chidern.size()>0 && ch.chidern.get(i).chidern.get(0).currentValue==2<<15){
				//��Ҷ�ӽڵ����û�и�ֵ
				complishSelf(ch.chidern.get(i));//��
			}
			
			if(ch.chidern.get(i).currentValue==2<<15){
				writeValue(ch.chidern.get(i), ch.chidern.get(i).chidern);

			}
		}
	}
	
	public void writeValue(UChess currentCh,ArrayList<UChess> chidern) {//ȡ�����е�ֵ
		int value;
		toGo=-1;
		if(currentCh.depth%2==0){//ȡ���ֵ
			value=Integer.MIN_VALUE;
			for(int i=0;i<chidern.size();i++){
				if(chidern.get(i).currentValue>value){
					value=chidern.get(i).currentValue;
					toGo=i;
				}
			}
		}else{
			//ȡ��Сֵ
			value=Integer.MAX_VALUE;
			for(int i=0;i<chidern.size();i++){
				if(chidern.get(i).currentValue<value){
					value=chidern.get(i).currentValue;
					toGo=i;
				}
			}
		}
		currentCh.currentValue=value;
		currentCh.step=toGo;
	}
	
	private class UChess{
	
		private ArrayList<UChess> chidern=new ArrayList<>();
		private int currentValue=2<<15;//��ǰֵ
		private int depth;//��ǰ��ȣ������ʼ�����Ϊ0,ż��ȡ���ֵ����ȡ��Сֵ
		@SuppressWarnings("unused")
		private UChess parent;
		private int[][] innerData;
		private int step;
	}
	

}
