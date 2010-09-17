package com.self_managment.web.util;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;

public class MySessionFilter extends OpenSessionInViewFilter {
    /*
     * The default mode is FlushMode.NEVER
     * 
     * @see org.springframework.orm.hibernate.support.OpenSessionInViewFilter#getSession
     *      (net.sf.hibernate.SessionFactory)
     */
    protected Session getSession(SessionFactory sessionFactory)
	    throws DataAccessResourceFailureException {
	super.setSingleSession(true);
	Session session = super.getSession(sessionFactory);
	// TODO ver esto!!
	session.setFlushMode(FlushMode.AUTO);
	return session;
    }

    /**
     * we do an explicit flush here just in case we do not have an automated
     * flush
     */
    protected void closeSession(Session session, SessionFactory factory) {
	try {
	    session.flush();
	} finally {
	    super.closeSession(session, factory);
	}
    }
}