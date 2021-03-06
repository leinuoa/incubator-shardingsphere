/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.core.parse.parser.sql.dml.delete;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.core.constant.DatabaseType;
import org.apache.shardingsphere.core.parse.lexer.LexerEngine;
import org.apache.shardingsphere.core.parse.parser.dialect.mysql.sql.MySQLDeleteParser;
import org.apache.shardingsphere.core.parse.parser.dialect.oracle.sql.OracleDeleteParser;
import org.apache.shardingsphere.core.parse.parser.dialect.postgresql.sql.PostgreSQLDeleteParser;
import org.apache.shardingsphere.core.parse.parser.dialect.sqlserver.sql.SQLServerDeleteParser;
import org.apache.shardingsphere.core.rule.ShardingRule;

/**
 * Delete parser factory.
 *
 * @author zhangliang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DeleteParserFactory {
    
    /**
     * Create delete parser instance.
     *
     * @param dbType database type
     * @param shardingRule databases and tables sharding rule
     * @param lexerEngine lexical analysis engine.
     * @return delete parser instance
     */
    public static AbstractDeleteParser newInstance(final DatabaseType dbType, final ShardingRule shardingRule, final LexerEngine lexerEngine) {
        switch (dbType) {
            case H2:
            case MySQL:
                return new MySQLDeleteParser(shardingRule, lexerEngine);
            case Oracle:
                return new OracleDeleteParser(shardingRule, lexerEngine);
            case SQLServer:
                return new SQLServerDeleteParser(shardingRule, lexerEngine);
            case PostgreSQL:
                return new PostgreSQLDeleteParser(shardingRule, lexerEngine);
            default:
                throw new UnsupportedOperationException(String.format("Cannot support database [%s].", dbType));
        }
    }
}
