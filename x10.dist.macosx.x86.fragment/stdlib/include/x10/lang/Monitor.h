#ifndef __X10_LANG_MONITOR_H
#define __X10_LANG_MONITOR_H

#include <x10rt.h>


#define X10_LANG_LOCK_H_NODEPS
#include <x10/lang/Lock.h>
#undef X10_LANG_LOCK_H_NODEPS
namespace x10 { namespace io { 
class SerialData;
} } 
namespace x10 { namespace lang { 
class UnsupportedOperationException;
} } 
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
class Runtime__Worker;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class Stack;
} } 
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace compiler { 
class Pinned;
} } 
namespace x10 { namespace lang { 

class Monitor : public x10::lang::Lock   {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::io::CustomSerialization::itable<x10::lang::Monitor > _itable_0;
    
    static x10::lang::Any::itable<x10::lang::Monitor > _itable_1;
    
    void _instance_init();
    
    void _constructor();
    
    static x10aux::ref<x10::lang::Monitor> _make();
    
    virtual x10aux::ref<x10::io::SerialData> serialize();
    void _constructor(x10aux::ref<x10::io::SerialData> id__132);
    
    static x10aux::ref<x10::lang::Monitor> _make(x10aux::ref<x10::io::SerialData> id__132);
    
    x10aux::ref<x10::util::Stack<x10aux::ref<x10::lang::Runtime__Worker> > >
      FMGL(workers);
    
    virtual void await();
    virtual void release();
    virtual x10aux::ref<x10::lang::Monitor> x10__lang__Monitor____x10__lang__Monitor__this(
      );
    void __fieldInitializers1952();
    
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
#endif // X10_LANG_MONITOR_H

namespace x10 { namespace lang { 
class Monitor;
} } 

#ifndef X10_LANG_MONITOR_H_NODEPS
#define X10_LANG_MONITOR_H_NODEPS
#include <x10/lang/Lock.h>
#include <x10/io/SerialData.h>
#include <x10/lang/UnsupportedOperationException.h>
#include <x10/lang/String.h>
#include <x10/lang/Runtime__Worker.h>
#include <x10/util/Stack.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/Int.h>
#include <x10/compiler/Pinned.h>
#ifndef X10_LANG_MONITOR_H_GENERICS
#define X10_LANG_MONITOR_H_GENERICS
template<class __T> x10aux::ref<__T> x10::lang::Monitor::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::Monitor> this_ = new (memset(x10aux::alloc<x10::lang::Monitor>(), 0, sizeof(x10::lang::Monitor))) x10::lang::Monitor();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_MONITOR_H_GENERICS
#endif // __X10_LANG_MONITOR_H_NODEPS
