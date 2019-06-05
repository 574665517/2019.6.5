package cn.tedu.game;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Brid {
	int x, y;
	int width, height;
	int size; // ����С��Ĵ�С��������ײ
	BufferedImage image;

	double g;// �������ٶ�
	double t;// ����λ�õļ������
	double v0;// ��ʼ�����ٶ�
	double speed;// �ǵ�ǰ�������ٶ�
	double s;// ����ʱ��t���λ��
	double alpha;// �����ǣ����ȵ�λ

	BufferedImage[] images;// ����һ������ͼƬ����Ķ���֡
	int index; // �Ƕ���֡��Ԫ�ص��±�λ��

	public Brid() throws Exception {
		image = ImageIO.read(getClass().getResource("xx0.png"));
		width = image.getWidth();
		height = image.getHeight();
		size = 40;
		x = 132;
		y = 280;

		g = 5;
		v0 = 20;
		t = 0.25;
		speed = v0;
		s = 0;
		alpha = 0;

		images = new BufferedImage[8];
		for (int i = 0; i < 8; i++) {
			// i = 0 1 2 3 4 5 6 7
			images[i] = ImageIO.read(getClass().getResource("xx"+ i + ".png"));
		}
		index = 0;

	}

	// ��Brid��ӷ���(fly)�Ĵ���
	public void fly() {
		index++;
		image = images[(index / 12) % 8];
	}

	// ���������ƶ�����
	public void run() {
		double v0 = speed;
		s = v0 * t + g * t * t / 2;// ��������λ���ٶ�
		y = y - (int) s;// �����������λ��
		double v = v0 - g * t;// �����´ε��ٶ�
		speed = v;

		alpha = Math.atan(s / 8);// ����API�ṩ�ķ����к������������
	}

	public void flappy() {
		// �������ó�ʼ�ٶȣ��������Ϸ�
		speed = v0;
	}

	// ��ײ�����Ĵ���
	public boolean hit(Ground ground) {
		boolean hit = y + size / 2 > ground.y;
		if (hit) {
			// ��������ڵ���
			y = ground.y - size / 2;
			// ʹ�����ʱ����ˤ����Ч��
			alpha = -3.14159265358979323 / 2;
		}
		return hit;
	}

	// ��ײ���ӵĴ���
	public boolean hit(Column column) {
		if (x > column.x - column.width / 2 - size / 2 && x < column.x + column.width / 2 + size / 2) {
			// ����Ƿ��ڷ�϶��
			if (y > column.y - column.gap / 2 + size / 2 && y < column.y + column.gap / 2 - size / 2) {
				return false;
			}
			return true;
		}
		return false;
	}
}
