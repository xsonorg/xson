package org.xson.core.serializer;

import org.xson.core.WriterModel;
import org.xson.core.XsonConst;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class CharacterSerializer extends DefaultSerializer {

	@Override
	public void write(Object target, WriterModel model) {
		Character x = (Character) target;
		// byte[] buf = XsonSupport.getCharDateFrame(x.charValue());
		// buf[0] = (byte) (buf[0] | XsonConst.BASIC_MASK);
		// model.append(buf);

		model.writeChar(x.charValue(), XsonConst.BASIC_MASK);
	}

}
