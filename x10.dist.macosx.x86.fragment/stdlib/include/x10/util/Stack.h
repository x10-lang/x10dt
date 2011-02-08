#ifndef __X10_UTIL_STACK_H
#define __X10_UTIL_STACK_H

#include <x10rt.h>


#define X10_UTIL_ARRAYLIST_H_NODEPS
#include <x10/util/ArrayList.h>
#undef X10_UTIL_ARRAYLIST_H_NODEPS
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace util { 

template<class FMGL(T)> class Stack;
template <> class Stack<void>;
template<class FMGL(T)> class Stack : public x10::util::ArrayList<FMGL(T)>
  {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[8];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static typename x10::util::Container<FMGL(T)>::template itable<x10::util::Stack<FMGL(T)> > _itable_0;
    
    static x10::lang::Any::itable<x10::util::Stack<FMGL(T)> > _itable_1;
    
    static typename x10::lang::Iterable<FMGL(T)>::template itable<x10::util::Stack<FMGL(T)> > _itable_2;
    
    static typename x10::util::Collection<FMGL(T)>::template itable<x10::util::Stack<FMGL(T)> > _itable_3;
    
    static typename x10::util::List<FMGL(T)>::template itable<x10::util::Stack<FMGL(T)> > _itable_4;
    
    static typename x10::util::Indexed<FMGL(T)>::template itable<x10::util::Stack<FMGL(T)> > _itable_5;
    
    static typename x10::lang::Settable<x10_int, FMGL(T)>::template itable<x10::util::Stack<FMGL(T)> > _itable_6;
    
    void _instance_init();
    
    void _constructor();
    
    static x10aux::ref<x10::util::Stack<FMGL(T)> > _make();
    
    virtual x10_boolean push(FMGL(T) v);
    virtual FMGL(T) pop();
    virtual x10aux::ref<x10::lang::Rail<FMGL(T) > > pop(x10_int k);
    virtual FMGL(T) peek();
    virtual x10_int search(FMGL(T) v);
    virtual x10aux::ref<x10::util::Stack<FMGL(T)> > x10__util__Stack____x10__util__Stack__this(
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
template <> class Stack<void> : public x10::util::ArrayList<void>
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_UTIL_STACK_H

namespace x10 { namespace util { 
template<class FMGL(T)>
class Stack;
} } 

#ifndef X10_UTIL_STACK_H_NODEPS
#define X10_UTIL_STACK_H_NODEPS
#include <x10/util/ArrayList.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Int.h>
#include <x10/lang/Rail.h>
#ifndef X10_UTIL_STACK_H_GENERICS
#define X10_UTIL_STACK_H_GENERICS
template<class FMGL(T)> template<class __T> x10aux::ref<__T> x10::util::Stack<FMGL(T)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::util::Stack<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::util::Stack<FMGL(T)> >(), 0, sizeof(x10::util::Stack<FMGL(T)>))) x10::util::Stack<FMGL(T)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_UTIL_STACK_H_GENERICS
#ifndef X10_UTIL_STACK_H_IMPLEMENTATION
#define X10_UTIL_STACK_H_IMPLEMENTATION
#include <x10/util/Stack.h>


template<class FMGL(T)> typename x10::util::Container<FMGL(T)>::template itable<x10::util::Stack<FMGL(T)> >  x10::util::Stack<FMGL(T)>::_itable_0(&x10::util::Stack<FMGL(T)>::clone, &x10::util::Stack<FMGL(T)>::contains, &x10::util::Stack<FMGL(T)>::containsAll, &x10::util::Stack<FMGL(T)>::equals, &x10::util::Stack<FMGL(T)>::hashCode, &x10::util::Stack<FMGL(T)>::isEmpty, &x10::util::Stack<FMGL(T)>::iterator, &x10::util::Stack<FMGL(T)>::size, &x10::util::Stack<FMGL(T)>::toRail, &x10::util::Stack<FMGL(T)>::toString, &x10::util::Stack<FMGL(T)>::typeName);
template<class FMGL(T)> x10::lang::Any::itable<x10::util::Stack<FMGL(T)> >  x10::util::Stack<FMGL(T)>::_itable_1(&x10::util::Stack<FMGL(T)>::equals, &x10::util::Stack<FMGL(T)>::hashCode, &x10::util::Stack<FMGL(T)>::toString, &x10::util::Stack<FMGL(T)>::typeName);
template<class FMGL(T)> typename x10::lang::Iterable<FMGL(T)>::template itable<x10::util::Stack<FMGL(T)> >  x10::util::Stack<FMGL(T)>::_itable_2(&x10::util::Stack<FMGL(T)>::equals, &x10::util::Stack<FMGL(T)>::hashCode, &x10::util::Stack<FMGL(T)>::iterator, &x10::util::Stack<FMGL(T)>::toString, &x10::util::Stack<FMGL(T)>::typeName);
template<class FMGL(T)> typename x10::util::Collection<FMGL(T)>::template itable<x10::util::Stack<FMGL(T)> >  x10::util::Stack<FMGL(T)>::_itable_3(&x10::util::Stack<FMGL(T)>::add, &x10::util::Stack<FMGL(T)>::addAll, &x10::util::Stack<FMGL(T)>::addAllWhere, &x10::util::Stack<FMGL(T)>::clear, &x10::util::Stack<FMGL(T)>::clone, &x10::util::Stack<FMGL(T)>::contains, &x10::util::Stack<FMGL(T)>::containsAll, &x10::util::Stack<FMGL(T)>::equals, &x10::util::Stack<FMGL(T)>::hashCode, &x10::util::Stack<FMGL(T)>::isEmpty, &x10::util::Stack<FMGL(T)>::iterator, &x10::util::Stack<FMGL(T)>::remove, &x10::util::Stack<FMGL(T)>::removeAll, &x10::util::Stack<FMGL(T)>::removeAllWhere, &x10::util::Stack<FMGL(T)>::retainAll, &x10::util::Stack<FMGL(T)>::size, &x10::util::Stack<FMGL(T)>::toRail, &x10::util::Stack<FMGL(T)>::toString, &x10::util::Stack<FMGL(T)>::typeName);
template<class FMGL(T)> typename x10::util::List<FMGL(T)>::template itable<x10::util::Stack<FMGL(T)> >  x10::util::Stack<FMGL(T)>::_itable_4(&x10::util::Stack<FMGL(T)>::add, &x10::util::Stack<FMGL(T)>::addAll, &x10::util::Stack<FMGL(T)>::addAllWhere, &x10::util::Stack<FMGL(T)>::addBefore, &x10::util::Stack<FMGL(T)>::clear, &x10::util::Stack<FMGL(T)>::clone, &x10::util::Stack<FMGL(T)>::contains, &x10::util::Stack<FMGL(T)>::containsAll, &x10::util::Stack<FMGL(T)>::equals, &x10::util::Stack<FMGL(T)>::getFirst, &x10::util::Stack<FMGL(T)>::getLast, &x10::util::Stack<FMGL(T)>::hashCode, &x10::util::Stack<FMGL(T)>::indexOf, &x10::util::Stack<FMGL(T)>::indexOf, &x10::util::Stack<FMGL(T)>::indices, &x10::util::Stack<FMGL(T)>::isEmpty, &x10::util::Stack<FMGL(T)>::iterator, &x10::util::Stack<FMGL(T)>::iteratorFrom, &x10::util::Stack<FMGL(T)>::lastIndexOf, &x10::util::Stack<FMGL(T)>::lastIndexOf, &x10::util::Stack<FMGL(T)>::__apply, &x10::util::Stack<FMGL(T)>::__set, &x10::util::Stack<FMGL(T)>::remove, &x10::util::Stack<FMGL(T)>::removeAll, &x10::util::Stack<FMGL(T)>::removeAllWhere, &x10::util::Stack<FMGL(T)>::removeAt, &x10::util::Stack<FMGL(T)>::removeFirst, &x10::util::Stack<FMGL(T)>::removeLast, &x10::util::Stack<FMGL(T)>::retainAll, &x10::util::Stack<FMGL(T)>::reverse, &x10::util::Stack<FMGL(T)>::size, &x10::util::Stack<FMGL(T)>::sort, &x10::util::Stack<FMGL(T)>::sort, &x10::util::Stack<FMGL(T)>::subList, &x10::util::Stack<FMGL(T)>::toRail, &x10::util::Stack<FMGL(T)>::toString, &x10::util::Stack<FMGL(T)>::typeName);
template<class FMGL(T)> typename x10::util::Indexed<FMGL(T)>::template itable<x10::util::Stack<FMGL(T)> >  x10::util::Stack<FMGL(T)>::_itable_5(&x10::util::Stack<FMGL(T)>::clone, &x10::util::Stack<FMGL(T)>::contains, &x10::util::Stack<FMGL(T)>::containsAll, &x10::util::Stack<FMGL(T)>::equals, &x10::util::Stack<FMGL(T)>::hashCode, &x10::util::Stack<FMGL(T)>::isEmpty, &x10::util::Stack<FMGL(T)>::iterator, &x10::util::Stack<FMGL(T)>::__apply, &x10::util::Stack<FMGL(T)>::size, &x10::util::Stack<FMGL(T)>::toRail, &x10::util::Stack<FMGL(T)>::toString, &x10::util::Stack<FMGL(T)>::typeName);
template<class FMGL(T)> typename x10::lang::Settable<x10_int, FMGL(T)>::template itable<x10::util::Stack<FMGL(T)> >  x10::util::Stack<FMGL(T)>::_itable_6(&x10::util::Stack<FMGL(T)>::equals, &x10::util::Stack<FMGL(T)>::hashCode, &x10::util::Stack<FMGL(T)>::__set, &x10::util::Stack<FMGL(T)>::toString, &x10::util::Stack<FMGL(T)>::typeName);
template<class FMGL(T)> x10aux::itable_entry x10::util::Stack<FMGL(T)>::_itables[8] = {x10aux::itable_entry(&x10aux::getRTT<x10::util::Container<FMGL(T)> >, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Iterable<FMGL(T)> >, &_itable_2), x10aux::itable_entry(&x10aux::getRTT<x10::util::Collection<FMGL(T)> >, &_itable_3), x10aux::itable_entry(&x10aux::getRTT<x10::util::List<FMGL(T)> >, &_itable_4), x10aux::itable_entry(&x10aux::getRTT<x10::util::Indexed<FMGL(T)> >, &_itable_5), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Settable<x10_int, FMGL(T)> >, &_itable_6), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::util::Stack<FMGL(T)> >())};
template<class FMGL(T)> void x10::util::Stack<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::util::Stack<FMGL(T)>");
    
}


//#line 15 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Stack.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::util::Stack<FMGL(T)>::_constructor() {
    this->::x10::util::ArrayList<FMGL(T)>::_constructor();
    {
     
    }
    
}
template<class FMGL(T)> x10aux::ref<x10::util::Stack<FMGL(T)> > x10::util::Stack<FMGL(T)>::_make(
                          ) {
    x10aux::ref<x10::util::Stack<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::util::Stack<FMGL(T)> >(), 0, sizeof(x10::util::Stack<FMGL(T)>))) x10::util::Stack<FMGL(T)>();
    this_->_constructor();
    return this_;
}



//#line 18 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Stack.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_boolean x10::util::Stack<FMGL(T)>::push(
  FMGL(T) v) {
    
    //#line 18 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Stack.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::Stack<FMGL(T)> >)this)->add(v);
    
}

//#line 21 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Stack.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::util::Stack<FMGL(T)>::pop() {
    
    //#line 21 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Stack.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::Stack<FMGL(T)> >)this)->removeLast();
    
}

//#line 23 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Stack.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::lang::Rail<FMGL(T) > > x10::util::Stack<FMGL(T)>::pop(
  x10_int k) {
    
    //#line 24 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Stack.x10": x10.ast.X10LocalDecl_c
    x10_int n = ((x10aux::ref<x10::util::Stack<FMGL(T)> >)this)->size();
    
    //#line 25 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Stack.x10": x10.ast.X10If_c
    if (((n) < (k))) {
        
        //#line 26 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Stack.x10": x10.ast.X10Return_c
        return x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Rail<FMGL(T) > > >(X10_NULL);
        
    }
    
    //#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Stack.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::Stack<FMGL(T)> >)this)->moveSectionToRail(
             ((x10_int) ((n) - (k))),
             ((x10_int) ((n) - (((x10_int)1)))));
    
}

//#line 31 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Stack.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::util::Stack<FMGL(T)>::peek(
  ) {
    
    //#line 31 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Stack.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::Stack<FMGL(T)> >)this)->getLast();
    
}

//#line 40 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Stack.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_int x10::util::Stack<FMGL(T)>::search(
  FMGL(T) v) {
    
    //#line 41 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Stack.x10": x10.ast.X10LocalDecl_c
    x10_int i = ((x10aux::ref<x10::util::Stack<FMGL(T)> >)this)->lastIndexOf(
                  v);
    
    //#line 42 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Stack.x10": x10.ast.X10If_c
    if (((i) >= (((x10_int)0)))) {
        
        //#line 43 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Stack.x10": x10.ast.X10Return_c
        return ((x10_int) ((((x10aux::ref<x10::util::Stack<FMGL(T)> >)this)->size()) - (i)));
        
    } else {
        
        //#line 45 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Stack.x10": x10.ast.X10Return_c
        return ((x10_int)-1);
        
    }
    
}

//#line 14 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Stack.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::util::Stack<FMGL(T)> >
  x10::util::Stack<FMGL(T)>::x10__util__Stack____x10__util__Stack__this(
  ) {
    
    //#line 14 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Stack.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::Stack<FMGL(T)> >)this);
    
}
template<class FMGL(T)> const x10aux::serialization_id_t x10::util::Stack<FMGL(T)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::util::Stack<FMGL(T)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(T)> void x10::util::Stack<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::util::ArrayList<FMGL(T)>::_serialize_body(buf);
    
}

template<class FMGL(T)> void x10::util::Stack<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::util::ArrayList<FMGL(T)>::_deserialize_body(buf);
    
}

template<class FMGL(T)> x10aux::RuntimeType x10::util::Stack<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::util::Stack<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::Stack<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::util::ArrayList<FMGL(T)> >()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.util.Stack";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 1, parents, 1, params, variances);
}
#endif // X10_UTIL_STACK_H_IMPLEMENTATION
#endif // __X10_UTIL_STACK_H_NODEPS
