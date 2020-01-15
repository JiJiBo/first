/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.rulerbug.first.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author ccabt
 * @date 2018年11月27日 下午15:59:27
 */
public final class M extends HashMap<String, Object> {

    public M() {}

    public static M c() {
        M r = new M();
        return r;
    }

    public static M d(Object data) {
        M r = new M();
        r.put("data", data);
        return r;
    }

    public static M d(String key, Object data) {
        M r = new M();
        r.put(key, data);
        return r;
    }

    public M p(String key, Object value) {
        super.put(key, value);
        return this;
    }

    @Override
    public M put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
