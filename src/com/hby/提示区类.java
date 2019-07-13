package com.hby;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Files;
import java.util.List;

class 提示区类 extends JTextArea implements java.awt.event.KeyListener {

    public 提示区类() {
        增加log("# 如何使用");
        增加log("复制class文件(可以复制多个),在这里粘贴.");
        增加log("会将反编译的文件生成到桌面.");
        增加log("如果有同名文件会覆盖.");
        增加log("另外这里也可以粘贴文本.");
        增加log("");
        增加log("# 注意事项");
        增加log("本jar所在路径不能有中文.");
        增加log("");
        增加log("# 引用");
        增加log("使用[jd-core-1.0.5](https://github.com/java-decompiler/jd-core).");
        增加log("");
        增加log("# 本程序源码");
        增加log("https://github.com/lsby/java-fby");
    }

    void 清空log() {
        setText("");
    }

    void 增加log(String s) {
        setText(getText() + s + "\n");
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
            清空log();

            try {
                Transferable cliptf = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
                if (cliptf == null || !cliptf.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
                    return;

                List 文件组 = (List) cliptf.getTransferData(DataFlavor.javaFileListFlavor);
                for (Object 文件对象 : 文件组) {
                    File 文件 = (File) 文件对象;

                    if (!文件.getName().contains(".class")) {
                        增加log("不是class文件");
                        continue;
                    }

                    File 拷贝文件 = new File(
                            Class.class.getClass().getResource("/").getPath().concat("/temp.class"));
                    File 生成文件 = new File(
                            FileSystemView.getFileSystemView().getHomeDirectory().getPath().concat("/")
                                    .concat(文件.getAbsolutePath()
                                            .replaceAll(":", "_")
                                            .replaceAll("/", "_")
                                            .replaceAll("\\\\", "_"))
                                    .concat(".java"));

                    if (拷贝文件.exists())
                        Files.delete(拷贝文件.toPath());
                    if (生成文件.exists())
                        Files.delete(生成文件.toPath());
                    Files.copy(文件.toPath(), 拷贝文件.toPath());
                    Files.write(生成文件.toPath(), 反编译模块.反编译().getBytes());

                    增加log(生成文件.getAbsoluteFile().toString());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}