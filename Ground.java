package cn.tedu.game;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Ground {
	int x, y; // ����x,y����
	BufferedImage image;// ͼƬ
	int width, height;// ����Ŀ��

	public Ground() {
		try {// ���ܻ���ִ���
			image = ImageIO.read(getClass().getResource("ground1.png"));
			x = 0;
			y = 500;
			width = image.getWidth();
			height = image.getHeight();

		} catch (Exception e) {
		}

	}

	public void run() {// ������һ��
		x--;
		if (x < -109) {
			x = 0;
		}
	}
}
