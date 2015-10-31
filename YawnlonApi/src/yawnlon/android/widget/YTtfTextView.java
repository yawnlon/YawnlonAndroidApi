package yawnlon.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import yawnlon.android.R;

public class YTtfTextView extends TextView {
	private String ttfPath; // ttf字体文件位置，需放置在assets目录下
	private String ttfPath_B; // ttf粗体字体文件位置，需放置在assets目录下

	public YTtfTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public YTtfTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		/* 这里取得declare-styleable集合 */
		TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.YTtfTextView);
		/* 这里从集合里取出相对应的属性值,第二参数是如果使用者没用配置该属性时所用的默认值 */
		ttfPath = typeArray.getString(R.styleable.YTtfTextView_font_path);
		ttfPath_B = typeArray.getString(R.styleable.YTtfTextView_font_path_b);
		typeArray.recycle();
	}

	public YTtfTextView(Context context) {
		super(context);
	}

	public void setTypeface(String path) {
		setTypeface(path, ttfPath_B);
	}

	public void setTypeface(String path, String path_B) {
		this.ttfPath = path;
		this.ttfPath_B = path_B;
		if (ttfPath == null) {
			return;
		}
		setTtf(getTypeface().getStyle());
	}

	public void setTypeface(Typeface tf) {
		if (ttfPath == null) {
			super.setTypeface(tf);
			return;
		}
		super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), ttfPath));
	}

	public void setTypeface(Typeface tf, int style) {
		if (ttfPath == null) {
			super.setTypeface(tf, style);
			return;
		}
		setTtf(style);
	}

	private void setTtf(int style) {
		if (style == Typeface.BOLD) {
			super.setTypeface(Typeface.createFromAsset(getContext().getAssets(),
					TextUtils.isEmpty(ttfPath_B) ? ttfPath : ttfPath_B));
		} else {
			super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), ttfPath));
		}
	}
}
