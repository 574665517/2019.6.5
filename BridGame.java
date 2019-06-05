package cn.tedu.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BridGame extends JPanel {

	private static final long serialVersionUID = 5652050328525654012L;
	
	BufferedImage background;
	Ground ground;
	Column column1;
	Column column2;
	Brid brid;
	// ����
	int score;
	BufferedImage startImage;

	/** ��Ϸ״̬ */
	int state;
	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int GAME_OVER = 2;

	// boolean gameOver;
	BufferedImage gameOverImage;

	public BridGame() throws Exception {

		state = START;
		// gameOver = false;
		startImage = ImageIO.read(getClass().getResource("start up.png"));
		background = ImageIO.read(getClass().getResource("new bg.png"));
		gameOverImage = ImageIO.read(getClass().getResource("game over.png"));
		ground = new Ground();
		column1 = new Column(1);
		column2 = new Column(2);
		brid = new Brid();

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(background, 0, 0, null);
		g.drawImage(column1.image, column1.x - column1.width / 2, column1.y - column1.height / 2, null);
		g.drawImage(column2.image, column2.x - column2.width / 2, column2.y - column2.height / 2, null);
		g.drawImage(ground.image, ground.x, ground.y, null);
		// ��ת(rotate)��ͼ����ϵ����API����
		g2.rotate(-brid.alpha, brid.x, brid.y);
		g.drawImage(brid.image, brid.x - brid.width / 2, brid.y - brid.height / 2, null);
		g2.rotate(brid.alpha, brid.x, brid.y);

		// ��paint��������ӻ��Ʒ������㷨
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 40);
		g.setFont(f);
		g.drawString("" + score, 40, 60);
		g.setColor(Color.WHITE);
		g.drawString("" + score, 40 - 3, 60 - 3);

		/**
		 * if(gameOver){ g.drawImage(gameOverImage,0,0,null); }
		 */

		// ��paint�����������ʾ��Ϸ����״̬����
		switch (state) {
		case GAME_OVER:
			g.drawImage(gameOverImage, 0, 0, null);
			break;
		case START:
			g.drawImage(startImage, 0, 0, null);
			break;
		}
	}

	// ��ʾ��Ϸ���̵Ŀ���
	public void action() throws Exception {
		MouseListener l = new MouseAdapter() {
			// ��갴��
			public void mousePressed(MouseEvent e) {
				// �����Ϸ�
				// brid.flappy();
				try {
					switch (state) {
					case GAME_OVER:
						column1 = new Column(1);
						column2 = new Column(2);
						brid = new Brid();
						score = 0;
						state = START;
						break;
					case START:
						state = RUNNING;
					case RUNNING:
						// �����Ϸ���
						brid.flappy();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		// ��1�ҽӵ���ǰ�������
		addMouseListener(l);

		while (true) {
			/**
			 * if(!gameOver){ brid.run(); brid.fly(); column1.run(); column2.run();
			 * ground.run(); System.out.println(ground.x); } if(brid.hit(ground)
			 * ||brid.hit(column1) ||brid.hit(column2)){ gameOver = true; } //��������߼�
			 * if(brid.x == column1.x ||brid.x == column2.x){ score++;
			 * 
			 * }
			 */

			switch (state) {
			case START:
				brid.fly();
				ground.run();
				break;
			case RUNNING:
				column1.run();
				column2.run();
				brid.run();// �����ƶ�
				brid.fly();// �Ӷ����
				ground.run();// �����ƶ�
				// �Ʒ��߼�
				if (brid.x == column1.x || brid.x == column2.x) {
					score++;
				}
				// �����ײ�ϵ�����Ϸ�ͽ���
				if (brid.hit(ground) || brid.hit(column1) || brid.hit(column2)) {
					state = GAME_OVER;
				}
				break;
			}
			repaint();// �ػ�
			Thread.sleep(20);
		}
	}

	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame();
		BridGame game = new BridGame();
		frame.add(game);
		frame.setSize(440, 670);// ���ÿ��
		frame.setLocationRelativeTo(null);// ���ھ���
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �����ر�ʹ���������˳�
		frame.setVisible(true);// ���ô���ɼ���
		game.action();
	}
}
