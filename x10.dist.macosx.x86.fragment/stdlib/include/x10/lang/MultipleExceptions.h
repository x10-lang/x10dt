#ifndef __X10_LANG_MULTIPLEEXCEPTIONS_H
#define __X10_LANG_MULTIPLEEXCEPTIONS_H

#include <x10rt.h>


#define X10_LANG_EXCEPTION_H_NODEPS
#include <x10/lang/Exception.h>
#undef X10_LANG_EXCEPTION_H_NODEPS
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
class Throwable;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class Stack;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace io { 
class Printer;
} } 
namespace x10 { namespace lang { 

class MultipleExceptions : public x10::lang::Exception   {
    public:
    RTT_H_DECLS_CLASS
    
    x10aux::ref<x10::array::Array<x10aux::ref<x10::lang::Throwable> > > FMGL(exceptions);
    
    void _instance_init();
    
    void _constructor(x10aux::ref<x10::util::Stack<x10aux::ref<x10::lang::Throwable> > > stack);
    
    static x10aux::ref<x10::lang::MultipleExceptions> _make(x10aux::ref<x10::util::Stack<x10aux::ref<x10::lang::Throwable> > > stack);
    
    void _constructor(x10aux::ref<x10::lang::Throwable> t);
    
    static x10aux::ref<x10::lang::MultipleExceptions> _make(x10aux::ref<x10::lang::Throwable> t);
    
    virtual void printStackTrace();
    virtual void printStackTrace(x10aux::ref<x10::io::Printer> p);
    static x10aux::ref<x10::lang::MultipleExceptions> make(x10aux::ref<x10::util::Stack<x10aux::ref<x10::lang::Throwable> > > stack);
    static x10aux::ref<x10::lang::MultipleExceptions> make(x10aux::ref<x10::lang::Throwable> t);
    x10aux::ref<x10::array::Array<x10aux::ref<x10::lang::Throwable> > > exceptions(
      );
    virtual x10aux::ref<x10::lang::MultipleExceptions> x10__lang__MultipleExceptions____x10__lang__MultipleExceptions__this(
      );
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_LANG_MULTIPLEEXCEPTIONS_H

namespace x10 { namespace lang { 
class MultipleExceptions;
} } 

#ifndef X10_LANG_MULTIPLEEXCEPTIONS_H_NODEPS
#define X10_LANG_MULTIPLEEXCEPTIONS_H_NODEPS
#include <x10/lang/Exception.h>
#include <x10/array/Array.h>
#include <x10/lang/Throwable.h>
#include <x10/util/Stack.h>
#include <x10/lang/Iterator.h>
#include <x10/io/Printer.h>
#ifndef X10_LANG_MULTIPLEEXCEPTIONS_H_GENERICS
#define X10_LANG_MULTIPLEEXCEPTIONS_H_GENERICS
template<class __T> x10aux::ref<__T> x10::lang::MultipleExceptions::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::MultipleExceptions> this_ = new (memset(x10aux::alloc<x10::lang::MultipleExceptions>(), 0, sizeof(x10::lang::MultipleExceptions))) x10::lang::MultipleExceptions();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_MULTIPLEEXCEPTIONS_H_GENERICS
#endif // __X10_LANG_MULTIPLEEXCEPTIONS_H_NODEPS
