package my.sample.domain.service;

import my.sample.domain.model.Book;
import my.sample.domain.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MultiThreadServiceImpl extends Thread implements MultiThreadService {

    @Autowired
    BookRepository bookRepository;

    int threadNum;

    @Transactional
    public void execute() {

        int threadCount = 2;
        MultiThreadServiceImpl[] services = new MultiThreadServiceImpl[threadCount];
        for (int i = 0; i < threadCount; i++) {
            services[i] = new MultiThreadServiceImpl();
            services[i].bookRepository = this.bookRepository;
            services[i].threadNum = i + 1;
        }

        for (int i = 0; i < threadCount; i++) {
            services[i].start();
        }
    }

    @Override
    public void run() {

        int count = 1000;

        for (int i = 0; i < count; i++) {
            String threadNumStr = new Integer(threadNum).toString();
            String numStr = new Integer(i + 1).toString();
            Book book;
            if (threadNum >= 10) {
                book = new Book("isbn00000000" + threadNumStr, "thread " + threadNumStr, "update count: " + numStr, null);
            } else {
                book = new Book("isbn000000000" + threadNumStr, "thread " + threadNumStr, "update count: " + numStr, null);
            }
            try {
                bookRepository.update(book);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }


}
